package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ProfileRole> role;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

}
