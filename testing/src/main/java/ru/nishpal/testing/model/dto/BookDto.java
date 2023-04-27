package ru.nishpal.testing.model.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nishpal.testing.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookDto {
    @NotNull(message = "Book name is null")
    @Size(min = 4, max = 30, message = "Book name is too short oe long")
    private String nameBook;
    @NotNull(message = "Author is empty")
    @Size(min = 4, max = 30, message = "Author is too short oe long")
    private String author;
    @NotNull(message = "Genre is null")
    @Size(min = 2, max = 30, message = "Genre is too short oe long")
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
