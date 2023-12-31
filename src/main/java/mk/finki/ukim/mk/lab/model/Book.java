package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private BookStore bookStore;
    private List<Author> authors;
}
