package ru.nishpal.testing.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.nishpal.testing.model.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class BookRepository {
    private List<Book> books = new ArrayList<>(List.of(
            new Book(1L, "Master and Margarita", "Bulgakov","Roman"),
            new Book(2L, "War and Peace", "Tolstoy","Military prose"),
            new Book(3L, "Crime and Punishment", "Dostoyevsky", "Roman"),
            new Book(4L, "Fathers and childrens", "Turgenev","Roman")
    ));
}
