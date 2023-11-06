package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/bookList")
public class BookListServlet extends HttpServlet implements BookService {

    private final BookRepository bookRepository;

    private final BookService bookService;

    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookRepository bookRepository, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.listBooks();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);
        context.setVariable("books", bookService.listBooks());
        context.setVariable("ipAddress", req.getRemoteAddr());

        springTemplateEngine.process(
                "listBooks.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedIsbn = req.getParameter("bookIsbn");
        resp.sendRedirect("/author?selectedIsbn=" + selectedIsbn);
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        return bookRepository.addAuthorToBook(authorId, isbn);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }
}
