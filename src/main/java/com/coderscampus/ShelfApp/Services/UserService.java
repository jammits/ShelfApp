package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    public User save(User user) {

        return userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findById(Integer id) {
        return userRepo.findById(id).get();
    }

    public User findByName(String username) {
        return userRepo.findByUsername(username);

    }
}
