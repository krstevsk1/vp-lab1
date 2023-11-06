package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorService {
    private List<Author> authors;

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();

        authors.add(new Author(1L, "Author1", "Surname1", "biography"));
        authors.add(new Author(2L, "Author2", "Surname2", "biography"));
        authors.add(new Author(3L, "Author3", "Surname3", "biography"));
        authors.add(new Author(4L, "Author4", "Surname4", "biography"));
        authors.add(new Author(5L, "Author5", "Surname5", "biography"));


    }

    @Override
    public List<Author> listAuthors() {
        return authors;
    }

    @Override
    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .get();
    }
}
