package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

}
