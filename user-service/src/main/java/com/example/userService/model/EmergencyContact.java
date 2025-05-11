package com.example.userService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id") //  the name of column in table EmergencyContact that link the User
    @JsonBackReference
    private User user;

//    @Version
//    private Integer version;


    public EmergencyContact(String name, String phone, User user) {
        this.name = name;
        this.phone = phone;
        this.user = user;
    }

}
