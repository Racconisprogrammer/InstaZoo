package com.example.instazoo.service;

import com.example.instazoo.repository.UserRepository;
import com.example.instazoo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found with username " + username));
    }
}