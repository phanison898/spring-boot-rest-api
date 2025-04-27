package com.github.phanison898.cloudbox.service;

import com.github.phanison898.cloudbox.dto.login.LoginRequest;
import com.github.phanison898.cloudbox.dto.login.LoginResponse;
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
    private final JwtService jwtService;

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

    public LoginResponse loginUser(LoginRequest loginRequest) {
        logger.info("Attempting to log in user: {}", loginRequest.getUsername());

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            logger.warn("Invalid login attempt for username: {}", loginRequest.getUsername());
            return new LoginResponse("Invalid username or password.", null);
        }

        // Generate JWT token
        String jwtToken = jwtService.generateToken(loginRequest.getUsername());

        logger.info("User '{}' logged in successfully", loginRequest.getUsername());
        return new LoginResponse("Login successful!", jwtToken);
    }
}