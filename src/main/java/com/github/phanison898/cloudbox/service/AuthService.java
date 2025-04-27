package com.github.phanison898.cloudbox.service;

import com.github.phanison898.cloudbox.dto.signup.SignupRequest;
import com.github.phanison898.cloudbox.model.User;
import com.github.phanison898.cloudbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean registerUser(SignupRequest signupRequest) {
        // Check if the username already exists in the database
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return false;  // Username already exists
        }

        // Hash the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // Create a new User entity and save it
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(signupRequest.getEmail());

        userRepository.save(user);  // Save the new user to the database
        return true;  // Successful signup
    }
}
