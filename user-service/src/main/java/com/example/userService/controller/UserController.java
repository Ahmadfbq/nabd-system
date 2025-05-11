package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }



    // AddUser CRUD (C)  GET /api/v1/users
    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserIdentifier = authentication.getName();
        Long loggedInUserId = userService.getUserIdByUsername(loggedInUserIdentifier);

        if (!loggedInUserId.equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ليس لديك صلاحية للوصول إلى بيانات هذا المستخدم.");
        }
        return userService.getUserById(id);
    }


    // DeleteById CRUD (D)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // UPDATE  with id
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserIdentifier = authentication.getName();
        Long loggedInUserId = userService.getUserIdByUsername(loggedInUserIdentifier);

        if (!loggedInUserId.equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to update this user..");
        }
        return userService.updateUser(id, updatedUser);
    }



    //  method for creating a user with emergency contacts
    //    @PostMapping
    //    public User createUser(@RequestBody User user) {
    //        // ensure each contacts is linked with new user
    //          if (user.getEmergencyContacts() != null) {
    //            for (EmergencyContact contact : user.getEmergencyContacts()) {
    //              contact.setUser(user);
    //        }
    //    }
    //    return userService.saveUser(user);
    //     }


    @GetMapping("/me")
    public ResponseEntity<User> getMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            User currentUser = userService.getUserByEmail(userDetails.getUsername()); // use UserService to bring all user details
            if (currentUser != null) {
                return ResponseEntity.ok(currentUser);
            } else {
                return ResponseEntity.notFound().build(); //or Unauthorized depend on you needs
            }
        } else {
            // user is not authorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
