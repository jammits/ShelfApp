package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Review;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review findByUserAndBook(User foundUser, Book currentBook) {
        return reviewRepository.findByUserAndBook(foundUser, currentBook);
    }

    @Transactional
    public void deleteReview(Integer id) {
        reviewRepository.deleteReviewsByReviewId(id);
    }
}
