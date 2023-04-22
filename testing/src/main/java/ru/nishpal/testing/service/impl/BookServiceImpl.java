package ru.nishpal.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<Book> findBooksByGenre(List<Book> books, String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public void createBook(BookDto bookDto, List<Book> books) {
        books.add(BookDto.dtoToBook(bookDto, books.size() + 1));
    }

    @Override
    public Book findBookById(long id, List<Book> books) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteBookById(long id, List<Book> books) {
        Book book = findBookById(id, books);
        if (book != null)
            books.remove(book);
    }
}
