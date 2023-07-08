package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Bookshelf;
import com.coderscampus.ShelfApp.Repository.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookshelfService {

    @Autowired
    private BookshelfRepository bookshelfRepository;
    public Bookshelf save(Bookshelf bookshelf) {
       return bookshelfRepository.save(bookshelf);
    }

    public Bookshelf findBookshelf(Integer id) {
        return bookshelfRepository.findByBookshelfId(id);
    }

    public boolean containsBook(Integer bookId, Bookshelf bookshelf) {
        List<Book> books = bookshelf.getBooks();
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return true;
            }
        }
        return false;
    }

}
