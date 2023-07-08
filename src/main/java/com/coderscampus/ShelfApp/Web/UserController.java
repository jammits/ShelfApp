package com.coderscampus.ShelfApp.Web;

import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/overview")
    public String getProfile(@AuthenticationPrincipal User user, ModelMap model){
        User currentUser = userService.findById(user.getUserId());
        model.put("user", currentUser);
        return "overview";
    }
}
