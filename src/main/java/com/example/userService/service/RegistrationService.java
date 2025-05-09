package com.example.userService.service;

import com.example.userService.model.RegisterRequest;
import com.example.userService.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidatorService emailValidatorService;


    public String register(RegisterRequest request) {
//        System.out.println("Register method called with email: " + request.getEmail());

        // Validates the provided email address
        boolean isValidEmail = emailValidatorService.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalArgumentException("Invalid email");
        }

        // Creates new User object from the registration request
        User newUser = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        // Delegates the actual user creation and saving to the UserService
        return userService.signUpUser(newUser);

    }


}
