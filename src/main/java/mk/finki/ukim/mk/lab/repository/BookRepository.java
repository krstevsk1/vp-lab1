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
    private final BookStoreRepository bookStoreRepository;

    public BookRepository(AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        books.add(new Book(1L,"1", "Book1", "genre1", 2002, bookStoreRepository.findAll().get(0), new ArrayList<>()));
        books.add(new Book(2L,"2", "Book2", "genre2", 2002, bookStoreRepository.findAll().get(1), new ArrayList<>()));
        books.add(new Book(3L,"3", "Book3", "genre3", 2002, bookStoreRepository.findAll().get(2), new ArrayList<>()));
        books.add(new Book(4L,"4", "Book4", "genre4", 2002, bookStoreRepository.findAll().get(3), new ArrayList<>()));
        books.add(new Book(5L,"5", "Book5", "genre5", 2002, bookStoreRepository.findAll().get(4), new ArrayList<>()));
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

    @Override
    public String saveBook(String name, String isbn, String genre, int year, int id) {
        Book book = new Book((long) books.size(), isbn, name, genre, year, bookStoreRepository.findAll().get(id), new ArrayList<>());
        books.add(book);
        return "redirect:/listBooks";
    }

    @Override
    public String editBook(Long bookId, String title, String isbn, String genre, int year, int id) {
        Book book = books.stream()
                .filter(b -> Objects.equals(b.getId(), bookId))
                .findFirst()
                .get();

        Book newBook = new Book(bookId, title, isbn, genre, year, bookStoreRepository.findAll().get(id), book.getAuthors());
        books.remove(book);
        books.add(newBook);

        return "redirect:/listBooks";
    }

    @Override
    public String deleteBook(Long id) {
        Book book = books.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .get();

        books.remove(book);

        return "redirect:/listBooks";
    }
}
