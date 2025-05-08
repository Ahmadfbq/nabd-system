package com.example.userService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "users") //
public class User implements UserDetails {

    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id; // its better to use Long for ids in DB
//    @Version
//    private Integer version;

    private String name;
    private String email;
    private String phone;
    private Integer height;
    private Integer weight;
    private Boolean emergencyEnabled;

    @Getter
    @JsonIgnore
    private String password;// Jackson: Ignore this field during JSON serialization

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonManagedReference // Jackson: Indicates the managing side for JSON serialization
    private List<EmergencyContact> emergencyContacts;


    // Constructor with all fields except ID
    public User(String name, String email, String phone, Integer height, Integer weight, String password, Boolean emergencyEnabled, List<EmergencyContact> emergencyContacts) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.password = password;
        this.emergencyEnabled = emergencyEnabled;
        this.emergencyContacts = emergencyContacts;
    }

    // Constructor for creating a user with basic registration info

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // implement UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return email; // Email used as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
