package com.github.phanison898.cloudbox.service;

import com.github.phanison898.cloudbox.dto.signup.SignupRequest;
import com.github.phanison898.cloudbox.dto.signup.SignupResponse;
import com.github.phanison898.cloudbox.model.User;
import com.github.phanison898.cloudbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignupResponse registerUser(SignupRequest signupRequest) {
        logger.info("Attempting to register user: {}", signupRequest.getUsername());

        try {
            // Check if the username already exists
            if (userRepository.existsByUsername(signupRequest.getUsername())) {
                logger.warn("Username '{}' already exists", signupRequest.getUsername());
                return new SignupResponse("Username already exists.", false);
            }

            // Hash the password
            String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

            // Create and save the user
            User user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setPassword(encodedPassword);
            user.setEmail(signupRequest.getEmail());

            userRepository.save(user);
            logger.info("User '{}' registered successfully", signupRequest.getUsername());

            return new SignupResponse("Signup successful!", true);
        } catch (Exception e) {
            logger.error("Error during user registration for '{}'", signupRequest.getUsername(), e);
            return new SignupResponse("An error occurred during signup. Please try again later.", false);
        }
    }
}