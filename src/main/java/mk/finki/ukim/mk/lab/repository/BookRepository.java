package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository implements BookService {
    private List<Book> books;
    private final AuthorRepository authorRepository;

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        books.add(new Book("1234", "Book", "genre", 2002, new ArrayList<>()));
        books.add(new Book("1234", "Book", "genre", 2002, new ArrayList<>()));
        books.add(new Book("1234", "Book", "genre", 2002, new ArrayList<>()));
        books.add(new Book("1234", "Book", "genre", 2002, new ArrayList<>()));
        books.add(new Book("1234", "Book", "genre", 2002, new ArrayList<>()));
    }

    @Override
    public List<Book> listBooks() {
        return books;
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId);
        Book book = this.findBookByIsbn(isbn);
        if (!book.getAuthors().contains(author)) {
            book.getAuthors().add(author);
        }
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return books.stream()
                .filter(b -> Objects.equals(b.getIsbn(), isbn))
                .findFirst()
                .get();
    }
}