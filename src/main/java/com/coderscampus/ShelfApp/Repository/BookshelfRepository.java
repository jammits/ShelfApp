package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Integer> {

}
