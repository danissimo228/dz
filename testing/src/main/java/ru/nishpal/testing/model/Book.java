package ru.nishpal.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private long id;
    private String nameBook;
    private String author;
    private String genre;
}
