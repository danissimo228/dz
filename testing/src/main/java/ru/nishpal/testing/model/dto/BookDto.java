package ru.nishpal.testing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nishpal.testing.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookDto {
    private String nameBook;
    private String author;
    private String genre;

    public static BookDto bookToDto(Book book) {
        return new BookDto(
                book.getNameBook(),
                book.getAuthor(),
                book.getGenre()
        );
    }

    public static Book dtoToBook(BookDto bookDto, long id) {
        return new Book(
                id,
                bookDto.getNameBook(),
                bookDto.getAuthor(),
                bookDto.getGenre()
        );
    }

    public static List<BookDto> bookListToDto(List<Book> books) {
        return books.stream()
                .map(BookDto::bookToDto)
                .collect(Collectors.toList());
    }
}
