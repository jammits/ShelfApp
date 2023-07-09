package com.coderscampus.ShelfApp.Web;

import com.coderscampus.ShelfApp.Domain.Book;
import com.coderscampus.ShelfApp.Domain.Review;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Services.BookService;
import com.coderscampus.ShelfApp.Services.ReviewService;
import com.coderscampus.ShelfApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/submitreview/{bookId}")
    public String submitReview(@PathVariable String bookId, @AuthenticationPrincipal User user, @RequestParam Integer rating) {

        User foundUser = userService.findById(user.getUserId());
        Book currentBook = bookService.findById(Integer.parseInt(bookId));

        // Find existing review by the user for the book
        Review existingReview = reviewService.findByUserAndBook(foundUser, currentBook);

        if (existingReview != null) {
            // If the review exists, update it
            existingReview.setReviewStars(rating);
            reviewService.save(existingReview);
        } else {
            // If the review does not exist, create a new one
            Review newReview = new Review();
            newReview.setReviewStars(rating);
            newReview.setUser(foundUser);
            newReview.setBook(currentBook);
            reviewService.save(newReview);

            currentBook.setReviews(newReview);
            bookService.save(currentBook);

            foundUser.setReviews(newReview);
            userService.save(foundUser);
        }

        return "redirect:/book/" + bookId;
    }


    @PostMapping("/deletereviews/{bookId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteReviews(@PathVariable String bookId, @AuthenticationPrincipal User user) {
        User foundUser = userService.findById(user.getUserId());
        Book currentBook = bookService.findById(Integer.parseInt(bookId));

        Review existingReview = reviewService.findByUserAndBook(foundUser, currentBook);
        reviewService.deleteReview(existingReview.getReviewId());

        foundUser.getReviews().remove(existingReview);
        currentBook.getReviews().remove(existingReview);
        userService.save(foundUser);
        bookService.save(currentBook);



        return ResponseEntity.ok(true);
    }
}
