package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
