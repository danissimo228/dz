package ru.nishpal.testing.controller;

import org.springframework.web.bind.annotation.*;
import ru.nishpal.testing.model.Book;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/book")
public class ControllerBook {
    private List<Book> books = Stream.of(
            new Book(1L, "Master and Margarita", "Bulgakov","Roman"),
            new Book(2L, "War and Peace", "Tolstoy","Military prose"),
            new Book(3L, "Crime and Punishment", "Dostoyevsky", "Roman"),
            new Book(4L, "Fathers and childrens", "Turgenev","Roman")
    ).collect(Collectors.toList());

    private final BookService bookService;

    public ControllerBook(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return BookDto.bookListToDto(books);
    }

    @GetMapping("/{genre}")
    public List<BookDto> getBookByGenre(@PathVariable String genre) {
        return BookDto.bookListToDto(bookService.findBooksByGenre(this.books, genre));
    }

    @PostMapping
    public void postBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto, this.books);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id, this.books);
    }
}
