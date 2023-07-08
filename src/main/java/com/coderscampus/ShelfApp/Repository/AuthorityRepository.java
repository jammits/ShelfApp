package com.coderscampus.ShelfApp.Repository;

import com.coderscampus.ShelfApp.Domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

}
