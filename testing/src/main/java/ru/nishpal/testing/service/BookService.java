package ru.nishpal.testing.service;

import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(long id);
    List<Book> findBooksByGenre(String genre);
    void createBook(BookDto bookDto);
    void deleteBookById(long id);
}
