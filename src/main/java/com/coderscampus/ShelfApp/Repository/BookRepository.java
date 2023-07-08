package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByTitle(String title);

    List<Book> findAllByAuthor(String author);

    Book findAllByDescription(String description);

}
