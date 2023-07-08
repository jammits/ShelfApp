package com.coderscampus.ShelfApp.Services;

import com.coderscampus.ShelfApp.Domain.User;
import com.coderscampus.ShelfApp.Repository.UserRepository;
import com.coderscampus.ShelfApp.Security.CustomSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username or password is invalid.");
        }

        return new CustomSecurityUser(user);

    }
}
