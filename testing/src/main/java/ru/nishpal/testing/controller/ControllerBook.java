package ru.nishpal.testing.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.testing.model.dto.BookDto;
import ru.nishpal.testing.service.BookService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class ControllerBook {
    private final BookService bookService;
    public ControllerBook(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return BookDto.
                bookListToDto(bookService.findAllBooks());
    }

    @GetMapping("/{genre}")
    public List<BookDto> getBookByGenre(@PathVariable String genre) {
        return BookDto.
                bookListToDto(bookService.findBooksByGenre(genre));
    }

    @PostMapping
    public void postBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            bookService.createBook(bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
    }
}
