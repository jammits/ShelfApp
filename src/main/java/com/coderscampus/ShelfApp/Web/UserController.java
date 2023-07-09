package com.coderscampus.ShelfApp.Web;

import com.coderscampus.ShelfApp.Domain.User;

import com.coderscampus.ShelfApp.Services.AdminService;
import com.coderscampus.ShelfApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @GetMapping("/overview")
    public String getProfile(@AuthenticationPrincipal User user, ModelMap model){
        User currentUser = userService.findById(user.getUserId());
        model.put("user", currentUser);
        return "overview";
    }

    @PostMapping("/overview")
    public String redirectToProfile() {
        return "redirect:/overview";
    }


    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal User user, ModelMap model) {
        User currentUser = userService.findById(user.getUserId());
        model.put("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile() {
        return "redirect:/profile";
    }


    @PostMapping("/profile/edit")
    public String updateProfile(User user, @AuthenticationPrincipal User currentUser) {
        User foundUser = userService.findById(currentUser.getUserId());
        foundUser.setFirstname(user.getFirstname());
        foundUser.setLastname(user.getLastname());
        if (!user.getPassword().equals("")) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            foundUser.setPassword(hashedPassword);
        }

        userService.save(foundUser);
        return "redirect:/profile";
    }


    @GetMapping("/friends")
    public String getFriends(ModelMap model, @AuthenticationPrincipal User user) {
        User currentUser = userService.findById(user.getUserId());
        model.put("user", currentUser);
        return "friends";
    }


    @PostMapping("/friends")
    public String redirectToFriends() {
        return "redirect:/friends";
    }


    @PostMapping("/friends/add")
    @ResponseBody
    public ResponseEntity<Boolean> addFriend(@RequestBody String friendId, @AuthenticationPrincipal User user) {
        User currentUser = userService.findById(user.getUserId());
        List<User> checkId = adminService.getAllUsers();
        boolean containsId = false;
        Integer toCheck = Integer.parseInt(friendId);

        for (User person : checkId) {
            if (person.getUserId().equals(toCheck)) {
                containsId = true;
                break;
            }
        }

        if (!containsId) {
            return ResponseEntity.ok(false);
        }
        User friend = userService.findById(Integer.parseInt(friendId));

        if (friend == null || currentUser.getUserId().equals(friend.getUserId()) || currentUser.getFriends().contains(friend)) {
            return ResponseEntity.ok(false);
        }
        friend.getFriends().add(currentUser);
        currentUser.getFriends().add(friend);
        userService.save(friend);
        userService.save(currentUser);
        return ResponseEntity.ok(true);
    }


    @PostMapping("/friends/delete")
    @ResponseBody
    public ResponseEntity<Boolean> deleteFriend(@RequestBody String friendId, @AuthenticationPrincipal User user) {
        User currentUser = userService.findById(user.getUserId());
        List<User> checkId = adminService.getAllUsers();
        boolean containsId = false;
        Integer toCheck = Integer.parseInt(friendId);

        for (User person : checkId) {
            if (person.getUserId().equals(toCheck)) {
                containsId = true;
                break;
            }
        }

        if (!containsId) {
            return ResponseEntity.ok(false);
        }

        User friend = userService.findById(Integer.parseInt(friendId));

        if (friend == null || currentUser.getUserId().equals(friend.getUserId())|| !currentUser.getFriends().contains(friend)) {
            return ResponseEntity.ok(false);
        }
        friend.getFriends().remove(currentUser);
        currentUser.getFriends().remove(friend);
        userService.save(friend);
        userService.save(currentUser);
        return ResponseEntity.ok(true);

    }



}
