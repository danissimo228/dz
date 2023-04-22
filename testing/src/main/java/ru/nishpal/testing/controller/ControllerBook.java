package ru.nishpal.testing.controller;

import org.springframework.web.bind.annotation.*;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.repository.BookRepository;
import ru.nishpal.testing.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class ControllerBook {
    private BookRepository bookRepository;
    private final BookService bookService;

    public ControllerBook(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return BookDto.bookListToDto(bookRepository.getBooks());
    }

    @GetMapping("/{genre}")
    public List<BookDto> getBookByGenre(@PathVariable String genre) {
        return BookDto.bookListToDto(bookService.findBooksByGenre(bookRepository.getBooks(), genre));
    }

    @PostMapping
    public void postBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto, bookRepository.getBooks());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id, bookRepository.getBooks());
    }
}
