package com.example.userService.model;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterRequest {
    private final String name;
    private final String password;
    private final String email;
//    private final boolean emergencyEnabled;

}
