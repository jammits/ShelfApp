package com.coderscampus.ShelfApp.Web;


import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    @ResponseBody
    public ResponseEntity<String> saveBook(@RequestBody Book book) {
        String bookUrl;
        if (bookService.findByDescription(book.getDescription()) == null) {
            Book savedBook = bookService.save(book);
            bookUrl = "/book/" + savedBook.getBookId().toString();
        } else {
            Book savedBook = bookService.findByDescription(book.getDescription());
            bookUrl = "/book/" + savedBook.getBookId().toString();
        }
        return ResponseEntity.ok(bookUrl);
    }

    @GetMapping("/book/{bookId}")
    public String getBook(ModelMap model, @PathVariable String bookId) {
        Book findBook = bookService.findById(Integer.parseInt(bookId));
        model.put("book", findBook);
        return "book";
    }
}
