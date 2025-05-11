package com.example.userService.service;


import com.example.userService.security.auth.ChangePasswordRequest;
import com.example.userService.model.EmergencyContact; // تأكد من استيراد الكلاس الصحيح إذا كان في حزمة مختلفة
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.userService.model.User;
import com.example.userService.repository.UserRepository;
//import com.example.notification_service.view.NotificationDto;


import java.security.Principal;
import java.util.List;

@Service("userService")
@RequiredArgsConstructor
// @Transactional // يمكنك تفعيل إدارة المعاملات على مستوى الكلاس إذا لزم الأمر
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final static String USER_NOT_FOUND = " user with email %s not found";

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // تشفير كلمة المرور عند الحفظ لأول مرة
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return repository.findById(id).map(existingUser -> {
                    if (updatedUser.getName() != null) {
                        existingUser.setName(updatedUser.getName());
                    }
                    if (updatedUser.getPhone() != null) {
                        existingUser.setPhone(updatedUser.getPhone());
                    }
                    if (updatedUser.getEmail() != null) {
                        existingUser.setEmail(updatedUser.getEmail());
                    }

                    if (updatedUser.getHeight() != null) {
                        existingUser.setHeight(updatedUser.getHeight());
                    }
                    if (updatedUser.getWeight() != null) {
                        existingUser.setWeight(updatedUser.getWeight());
                    }
                    if (updatedUser.getPassword() != null) {
                        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // تشفير كلمة المرور عند التحديث
                    }
                    if (updatedUser.getEmergencyEnabled() != null) {
                        existingUser.setEmergencyEnabled(updatedUser.getEmergencyEnabled());
                    }

                    // Update emergency contacts (only if provided)
                    List<EmergencyContact> updatedContacts = updatedUser.getEmergencyContacts();
                    if (updatedContacts != null) {
                        List<EmergencyContact> existingContacts = existingUser.getEmergencyContacts();

                        // 1. Update existing contacts and add new ones
                        for (EmergencyContact updatedContact : updatedContacts) {
                            boolean found = false;
                            if (updatedContact.getId() != null) {
                                for (EmergencyContact existingContact : existingContacts) {
                                    if (updatedContact.getId().equals(existingContact.getId())) {
                                        existingContact.setName(updatedContact.getName());
                                        existingContact.setPhone(updatedContact.getPhone());
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            // 2. If not exist ,Link new contact to the user
                            if (!found && updatedContact.getId() == null) {
                                updatedContact.setUser(existingUser); // link it with user
                                existingUser.getEmergencyContacts().add(updatedContact);
                            }
                        }
                    }

                    try {
                        return repository.save(existingUser);
                    } catch (ObjectOptimisticLockingFailureException e) {
                        throw new RuntimeException("Data has been updated by another user. Please refresh and try again.", e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    }

    public String signUpUser(User user) {
        boolean userExists = repository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalArgumentException("Email already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // تشفير كلمة المرور عند التسجيل
        repository.save(user);
        return "Registration successful!";
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // تحقق إذا كانت كلمة المرور الحالية صحيحة
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // تحقق إذا كانت كلمتا المرور الجديدتان متطابقتين
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // تحديث كلمة المرور
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // حفظ كلمة المرور الجديدة
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, email)));
    }

    public Long getUserIdByUsername(String email) {
        return repository.findByEmail(email)
                .map(user -> user.getId().longValue())
                .orElseThrow(() -> new UsernameNotFoundException("لم يتم العثور على مستخدم بالبريد الإلكتروني: " + email));
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("لم يتم العثور على مستخدم بالبريد الإلكتروني: " + email));
    }

//    public void updateUserHealthStatus(NotificationDto notification) {
//        User user = repository.findById(notification.getUserID())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setHealthStatus(notification.getMessage());
//        repository.save(user);
//    }
}
