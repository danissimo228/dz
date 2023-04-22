package ru.nishpal.testing.service.impl;

import org.junit.jupiter.api.Test;
import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {
    private List<Book> books = Stream.of(
            new Book(1L, "Master and Margarita", "Bulgakov","Roman"),
            new Book(2L, "War and Peace", "Tolstoy","Military prose"),
            new Book(3L, "Crime and Punishment", "Dostoyevsky", "Roman"),
            new Book(4L, "Fathers and childrens", "Turgenev","Roman")
    ).collect(Collectors.toList());
    private final BookService bookService = new BookServiceImpl();

    @Test
    void testFindBooksByGenreMethod() {
        assertEquals(bookService.findBooksByGenre(books, "random text"), new ArrayList<>());
        assertEquals(bookService.findBooksByGenre(books, "Roman"), List.of(books.get(0), books.get(2), books.get(3)));
    }

    @Test
    void testCreateBook() {
        BookDto bookDto = new BookDto("test", "test", "test");
        bookService.createBook(bookDto, books);
        assertEquals(BookDto.dtoToBook(bookDto, books.size()), books.get(books.size() - 1));
    }

    @Test
    void testDeleteBook() {
        long id = books.size();
        BookDto bookDto = BookDto.bookToDto(bookService.findBookById(id, books));
        bookService.deleteBookById(books.size(), books);
        long currentMaxId = books.size();
        BookDto currentBookDto = BookDto.bookToDto(bookService.findBookById(currentMaxId, books));
        assertNotEquals(currentBookDto, bookDto);
    }

    @Test
    void getBookWithMaxIdAfterDelete() {
        long id = books.size();
        bookService.deleteBookById(id, books);
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    books.get((int) (id - 1));
                });
    }
}