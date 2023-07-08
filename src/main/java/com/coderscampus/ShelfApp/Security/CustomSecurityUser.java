package com.coderscampus.ShelfApp.Security;

import com.coderscampus.ShelfApp.Domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomSecurityUser extends User implements UserDetails {

    public CustomSecurityUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setUserId(user.getUserId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
