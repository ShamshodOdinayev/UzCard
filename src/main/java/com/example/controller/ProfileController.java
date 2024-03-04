package com.example.controller;

import com.example.dto.CreateProfileDTO;
import com.example.enums.AppLanguage;
import com.example.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(name = "/profile")
@Tag(name = "API for profile list", description = "This api is Company employees")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CreateProfileDTO dto,
                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage language) {
        log.warn("Create profile {}", dto.getUsername());
        return ResponseEntity.ok(profileService.create(dto, language));
    }

}
