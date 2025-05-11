package com.example.userService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WearableDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String deviceIdentifier;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // متغير لتخزين البيانات مؤقتًا (يمكن تغييره إلى نوع أكثر تحديدًا لاحقًا)
    @Column(columnDefinition = "TEXT")
    private String cacheStorage;

    @Transient
    private Integer userID; // This field is transient and stores the user's ID

    public void pair(User user) {
        this.user = user;
        this.userID = user.getId(); // نسخ معرف المستخدم
    }

    public void sendData(String measurement) {
        // Implement sending data logic here
        System.out.println("Sending data: " + measurement + " from device " + this.deviceIdentifier);
    }

    public void storeLocallyInCache(String data) {
        this.cacheStorage = data;
        System.out.println("Storing data locally: " + data + " in device " + this.deviceIdentifier);
    }
}
