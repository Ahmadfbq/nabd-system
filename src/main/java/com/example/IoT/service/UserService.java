package com.example.IoT.service;


import com.example.IoT.model.EmergencyContact;
import com.example.IoT.model.User;
import com.example.IoT.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// everthing deal with logic
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
    return userRepository.findAll();
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalStateException(id + " not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setHeight(updatedUser.getHeight());
            existingUser.setWeight(updatedUser.getWeight());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmergencyEnabled(updatedUser.getEmergencyEnabled());

            // -------- تحديث جهات الاتصال --------
            List<EmergencyContact> updatedContacts = updatedUser.getEmergencyContacts();

            // 1. تعديل الموجودين
            for (EmergencyContact updatedContact : updatedContacts) {
                boolean found = false;
                for (EmergencyContact existingContact : existingUser.getEmergencyContacts()) {
                    if (updatedContact.getId() != null && updatedContact.getId().equals(existingContact.getId())) {
                        // تحديث البيانات
                        existingContact.setName(updatedContact.getName());
                        existingContact.setPhone(updatedContact.getPhone());
                        found = true;
                        break;
                    }
                }
                // 2. لو ما وجد، نضيفه جديد
                if (!found) {
                    updatedContact.setUser(existingUser); // ربطه بالـ user
                    existingUser.getEmergencyContacts().add(updatedContact);
                }
            }

            // 3. يمكن إضافة خطوة حذف الـ contacts التي لم تعد موجودة لو حبيت (اختياري)

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
