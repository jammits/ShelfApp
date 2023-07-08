package com.coderscampus.ShelfApp.Services;


import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);

    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    public Book findByDescription(String desc) {
        return bookRepository.findAllByDescription(desc);
    }

}
