package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        List<Book> books = bookService.listBooks();

        model.addAttribute("books", books);

        return "listBooks";
    }

    @PostMapping("/books/add")
    public String saveBook(String name, String isbn, String genre, int year, int id) {
        return bookService.saveBook(name, isbn, genre, year, id);
    }

    @PutMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable("bookId") Long bookId, String title, String isbn, String genre, int year, int id) {
        return bookService.editBook(bookId, title, isbn, genre, year, id);
    }

    @DeleteMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

}
