package com.example.instazoo.service;


import com.example.instazoo.entity.User;
import com.example.instazoo.entity.enums.RoleUser;
import com.example.instazoo.entity.request.SignUpRequest;
import com.example.instazoo.exception.UserExistException;
import com.example.instazoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getFirstname());
        user.setLastName(signUpRequest.getLastname());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.getRoleUsers().add(RoleUser.ROLE_USER);

        try {
            LOG.info("Saving User {}", signUpRequest.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + "already exists. Please check credentials");
        }
    }

}
