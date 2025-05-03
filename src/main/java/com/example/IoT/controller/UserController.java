package com.example.IoT.controller;

import com.example.IoT.model.EmergencyContact;
import com.example.IoT.model.User;
import com.example.IoT.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }



    // AddUser First One CRUD (C)
    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    // FindById First One CRUD (R)
    // bring user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    // إضافة مستخدم جديد مع جهات الاتصال الطارئة
 //   @PostMapping
//    public User createUser(@RequestBody User user) {
        // تأكد أن كل جهة اتصال مربوطة بالمستخدم الجديد
  //      if (user.getEmergencyContacts() != null) {
    //        for (EmergencyContact contact : user.getEmergencyContacts()) {
      //          contact.setUser(user);
        //    }
        //}
        //return userService.saveUser(user);
   // }

    // delete user by id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser);
    }



}
