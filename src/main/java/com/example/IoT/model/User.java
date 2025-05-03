package com.example.IoT.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "users") //
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // من الأفضل استخدام Long للمعرفات في قواعد البيانات
    private String name;
    private String email;
    private String phone;
    private Integer height;
    private Integer weight;
    @JsonIgnore
    private String password;
    private Boolean emergencyEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EmergencyContact> emergencyContacts;



    public User() {
    }

    public User(String name, String email, String phone, Integer height, Integer weight, String password, Boolean emergencyEnabled) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.password = password;
        this.emergencyEnabled = emergencyEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmergencyEnabled() {
        return emergencyEnabled;
    }

    public void setEmergencyEnabled(Boolean emergencyEnabled) {
        this.emergencyEnabled = emergencyEnabled;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }



}
