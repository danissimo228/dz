package ru.nishpal.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.nishpal.testing.exception.ApplicationException;
import ru.nishpal.testing.exception.ExceptionMessage;
import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.repository.BookRepository;
import ru.nishpal.testing.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.getBooks().stream()
                .filter(book -> book.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public void createBook(BookDto bookDto) {
        List<Book> books = bookRepository.getBooks();
        if (bookDto == null)
            throw new ApplicationException(ExceptionMessage.BOOK_IS_NULL);
        books.add(BookDto.dtoToBook(bookDto, books.size() + 1));
        bookRepository.setBooks(books);
    }

    @Override
    public Book findBookById(long id) {
        return bookRepository.getBooks().stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteBookById(long id) {
        List<Book> books = bookRepository.getBooks();
        Book book = findBookById(id);
        if (book == null)
            throw new ApplicationException(ExceptionMessage.BOOK_NOT_FOUND);
        books.remove(book);
        bookRepository.setBooks(books);
    }
}
