package com.example.userService.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // TODO regex to validate email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return s != null && s.matches(emailRegex);
    }
}
