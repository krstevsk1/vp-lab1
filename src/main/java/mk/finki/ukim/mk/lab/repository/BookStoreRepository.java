package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookStoreRepository implements BookStoreService {
    private List<BookStore> bookStores;


    @PostConstruct
    public void init() {
        bookStores = new ArrayList<>();
        bookStores.add(new BookStore(1L, "Store1", "City 1", "Address 1", new ArrayList<Book>()));
        bookStores.add(new BookStore(2L, "Store2", "City 2", "Address 2", new ArrayList<Book>()));
        bookStores.add(new BookStore(3L, "Store3", "City 3", "Address 3", new ArrayList<Book>()));
        bookStores.add(new BookStore(4L, "Store4", "City 4", "Address 4", new ArrayList<Book>()));
        bookStores.add(new BookStore(5L, "Store5", "City 5", "Address 5", new ArrayList<Book>()));
    }

    @Override
    public List<BookStore> findAll() {
        return bookStores;
    }
}
