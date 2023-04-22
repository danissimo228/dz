package ru.nishpal.testing.service;

import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;

import java.util.List;

public interface BookService {
    Book findBookById(long id, List<Book> books);
    List<Book> findBooksByGenre(List<Book> books, String genre);
    void createBook(BookDto bookDto, List<Book> books);
    void deleteBookById(long id, List<Book> books);
}
