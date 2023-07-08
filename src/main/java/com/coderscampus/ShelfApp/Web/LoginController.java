package com.coderscampus.ShelfApp.Web;

import com.coderscampus.ShelfApp.Domain.Authorities;
import com.coderscampus.ShelfApp.Domain.Bookshelf;
import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Services.AuthorityService;
import com.coderscampus.ShelfApp.Services.BookshelfService;
import com.coderscampus.ShelfApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    @Autowired
    private BookshelfService bookshelfService;

    @Autowired
    private AuthorityService authorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Boolean> registerUser(@RequestBody User user){

        // Check if a user with the given username already exists
        User existingUser = userService.findByName(user.getUsername());
        if (existingUser != null) {
            // If a user with the given username already exists, return false
            return ResponseEntity.ok(false);
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Authorities auth = new Authorities("ROLE_USER", user);
        user.getAuthorities().add(auth);

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUser(user);
        bookshelf.setBookshelfName(user.getUsername() +"'s Bookshelf");
        user.setBookshelf(bookshelf);

        userService.save(user);
        authorService.save(auth);
        bookshelfService.save(bookshelf);

        // If the registration process was successful, return true
        return ResponseEntity.ok(true);
    }
}
