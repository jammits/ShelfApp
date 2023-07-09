package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Review;
import com.coderscampus.ShelfApp.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findByUserAndBook(User foundUser, Book currentBook);

    void deleteReviewsByReviewId(Integer id);
}
