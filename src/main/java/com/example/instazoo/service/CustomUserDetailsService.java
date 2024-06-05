package com.example.instazoo.service;

import com.example.instazoo.entity.User;
import com.example.instazoo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;


    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        User user = userService.getUserByEmail(username);
        return new CustomUserDetails(user);
    }

    public CustomUserDetails loadUserById(Long id) {
        User customUserDetails = userService.getUserById(id);
        return new CustomUserDetails(customUserDetails);
    }
}
