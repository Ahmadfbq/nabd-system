package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    // FindById CRUD (R)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    // DELETE user by id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // UPDATE  with id
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
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



}
