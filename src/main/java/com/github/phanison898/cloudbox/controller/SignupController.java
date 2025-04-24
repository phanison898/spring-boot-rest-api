package com.github.phanison898.cloudbox.controller;

import com.github.phanison898.cloudbox.dto.signup.SignupRequest;
import com.github.phanison898.cloudbox.dto.signup.SignupResponse;
import com.github.phanison898.cloudbox.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        boolean success = signupService.registerUser(signupRequest);

        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SignupResponse("Signup successful!", true));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new SignupResponse("Username already exists.", false));
        }
    }
}
