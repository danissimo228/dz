package ru.nishpal.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.nishpal.testing.exception.ApplicationException;
import ru.nishpal.testing.exception.ExceptionMessage;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerBookTest {
    @Autowired
    BookService bookService;
    @Autowired
    ControllerBook controllerBook;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private static final String URL = "/api/v1/book";

    @Test
    public void getBooks_whenGetBooks_thenStatus200AndReturnsAllBooks() throws Exception {
        bookService.createBook(new BookDto("test", "test", "test"));
        List<BookDto> listOfBookDto = BookDto.bookListToDto(bookService.findAllBooks());

        mockMvc.perform(
                get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfBookDto)));
    }

    @Test
    public void getBookByGenre_whenBookWithGenreIsNotFound_ThenStatus200AndReturnsEmptyArray() throws Exception {
        String genre = "test random";

        mockMvc.perform(
                get(URL + "/{genre}", genre))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new ArrayList<>())));
    }

    @Test
    public void getBookByGenre_whenGetBooksByGenre_thenStatus200AndReturnsAllBooksWithGenre() throws Exception {
        String genre = "Roman";
        List<BookDto> listOfBooksDtoByGenre = BookDto.bookListToDto(bookService.findBooksByGenre(genre));

        mockMvc.perform(
                get(URL + "/{genre}", genre))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfBooksDtoByGenre)));
    }

    @Test
    public void deleteBook_whenDeleteBookWithGenre_thenStatus200() throws Exception {
        String genre = "Military prose";
        long id = bookService.findBooksByGenre(genre).get(0).getId();

        mockMvc.perform(
                delete(URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBook_whenBookIsNotFound_thenStatus400AndReturnsApplicationExceptionBookNotFound() throws Exception {
        long id = bookService.findAllBooks().size() + 1;

        mockMvc.perform(
                        delete(URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(mvcResult ->
                    mvcResult.getResolvedException().equals(new ApplicationException(ExceptionMessage.BOOK_NOT_FOUND)));
    }

    @Test
    public void postBook_whenCreateBook_thenStatus200AndLastBookEqualsCreatedBook() throws Exception {
        BookDto bookDto = new BookDto("test-post", "test-post", "test-post");

        mockMvc.perform(
                post(URL)
                        .content(objectMapper.writeValueAsString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void postBook_whenBookIsNull_thenStatus400AndReturnsApplicationExceptionBookIsNull() throws Exception {
        mockMvc.perform(
                post(URL)
                        .content(objectMapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult ->
                        mvcResult.getResolvedException().equals(new ApplicationException(ExceptionMessage.BOOK_IS_NULL)));
    }
}