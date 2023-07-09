package com.coderscampus.ShelfApp.Web;


import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Bookshelf;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Services.BookService;
import com.coderscampus.ShelfApp.Services.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookshelfController {

    @Autowired
    private BookshelfService bookshelfService;
    @Autowired
    private BookService bookService;

    @GetMapping("/bookshelf")
    public String getBookshelf(ModelMap model, @AuthenticationPrincipal User user) {
        Bookshelf bookshelf = bookshelfService.findBookshelf(user.getUserId());
        model.put("bookshelf", bookshelf);
        return "bookshelf";
    }

    @PostMapping("/bookshelf")
    public String redirectToBookshelf() {
        return "redirect:/bookshelf";
    }

    @GetMapping("/bookshelf/contains/{bookId}")
    @ResponseBody
    public ResponseEntity<Boolean> containsBook(@PathVariable String bookId, @AuthenticationPrincipal User user) {
        Bookshelf bookshelf = bookshelfService.findBookshelf(user.getUserId());
        Boolean containsBook = bookshelfService.containsBook(Integer.parseInt(bookId),bookshelf);
        return ResponseEntity.ok(containsBook);
    }

    @PostMapping("/bookshelf/add/{bookId}")
    @ResponseBody
    public ResponseEntity<Boolean> addBook(@PathVariable String bookId, @AuthenticationPrincipal User user) {
        Bookshelf bookshelf = bookshelfService.findBookshelf(user.getUserId());
        Book foundBook = bookService.findById(Integer.parseInt(bookId));
        bookshelf.setBooks(foundBook);

        foundBook.setBookshelf(bookshelf);

        bookshelfService.save(bookshelf);
        bookService.save(foundBook);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/bookshelf/delete/{bookId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteBook(@PathVariable String bookId, @AuthenticationPrincipal User user) {
        Bookshelf bookshelf = bookshelfService.findBookshelf(user.getUserId());
        Book foundBook = bookService.findById(Integer.parseInt(bookId));
        bookshelf.getBooks().remove(foundBook);

        foundBook.getBookshelf().remove(bookshelf);

        bookshelfService.save(bookshelf);
        bookService.save(foundBook);
        return ResponseEntity.ok(true);
    }
}
