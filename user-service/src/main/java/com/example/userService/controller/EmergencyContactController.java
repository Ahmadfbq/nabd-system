package com.example.userService.controller;

import com.example.userService.model.EmergencyContact;
import com.example.userService.repository.EmergencyContactRepository;
import com.example.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emergency-contacts")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactRepository emergencyContactRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addEmergencyContact(@RequestBody EmergencyContact request) {
        Integer userId = request.getUser() != null ? request.getUser().getId() : null;

        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID is missing");
        }

        return userRepository.findById(Long.valueOf(userId))
                .map(user -> {
                    EmergencyContact contact = new EmergencyContact(request.getName(), request.getPhone(), user);
                    emergencyContactRepository.save(contact);
                    return ResponseEntity.ok("Emergency contact saved");
                })
                .orElse(ResponseEntity.badRequest().body("User not found"));
    }

}
