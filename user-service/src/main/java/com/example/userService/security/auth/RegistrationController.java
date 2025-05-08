package com.example.userService.security.auth;


import com.example.userService.model.RegisterRequest;
import com.example.userService.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // marks this class as a REST controller
@RequestMapping(path = "api/v1/registration") // base path for all endpoints in this controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService; //Inject the RegistrationService

    @PostMapping // Maps HTTP POST requests to this method
    public String register(@RequestBody RegisterRequest request) {
        // Binds the request body (JSON) to the RegistrationRequest object
        return registrationService.register(request); // // delegate the registration logic to the service
    }

}
