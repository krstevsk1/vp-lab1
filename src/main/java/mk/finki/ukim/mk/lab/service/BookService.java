package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    String saveBook(String name, String isbn, String genre, int year, int id);
    String editBook(Long bookId, String title, String isbn, String genre, int year, int id);
    String deleteBook(Long id);
}
