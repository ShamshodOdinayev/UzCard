package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProfileDTO {
    private String name;
    private String surname;
    private List<ProfileRole> roleList;
    private String username;
    private String password;
}
