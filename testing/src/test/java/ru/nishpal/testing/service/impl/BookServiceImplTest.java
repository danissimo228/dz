package ru.nishpal.testing.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Зачем здесь нужен @Autowired

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceImplTest {
    private final BookService bookService;

    @Autowired
    public BookServiceImplTest(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    @Order(1)
    void FindBooksByGenre_WhenGenreIsRoman_ReturnAllBooksWhereGenreIsRoman() {
        String genre = "Roman";
        List<Book> booksWithGenreRoman = new ArrayList<>(List.of
                (
                        bookService.findAllBooks().get(0),
                        bookService.findAllBooks().get(2),
                        bookService.findAllBooks().get(3)
                ));

        assertEquals(bookService.findBooksByGenre(genre), booksWithGenreRoman);
    }

    @Test
    @Order(2)
    void FindBooksByGenre_WhenBookDoesNotExist_ReturnEmptyArrayList() {
        String genre = "random text";
        List<Book> exceptedArray = new ArrayList<>();

        List<Book> getBookByGenre = bookService.findBooksByGenre(genre);

        assertEquals(getBookByGenre, exceptedArray);
    }

    @Test
    @Order(3)
    void createBook_AfterCreateLastElementOfArrayEqualsCreatedBook_ReturnTrue() {
        BookDto bookDto = new BookDto("test", "test", "test");

        bookService.createBook(bookDto);
        Book book = BookDto.dtoToBook(bookDto, bookService.findAllBooks().size());
        Book lastBookInTheArray = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1);

        assertEquals(book, lastBookInTheArray);
    }

    @Test
    @Order(4)
    void deleteBook_GetDeletedBook_ReturnNull() {
        long id = bookService.findAllBooks().size();

        bookService.deleteBookById(id);

        assertNull(bookService.findBookById(id));
    }
}