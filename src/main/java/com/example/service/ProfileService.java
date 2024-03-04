package com.example.service;

import com.example.dto.CreateProfileDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.AppLanguage;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import com.example.util.MDUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ResourceBundleService resourceBundleService;

    public ProfileService(ProfileRepository profileRepository, ResourceBundleService resourceBundleService) {
        this.profileRepository = profileRepository;
        this.resourceBundleService = resourceBundleService;
    }

    public Object create(CreateProfileDTO dto, AppLanguage language) {
        checkUsername(dto.getUsername(), language);
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRoleList());
        entity.setUsername(dto.getUsername());
        profileRepository.save(entity);
        return toDTO(entity);
    }

    private static ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setUsername(entity.getUsername());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setRoleList(entity.getRole());
        return profileDTO;
    }

    private void checkUsername(String username, AppLanguage language) {
        Optional<ProfileEntity> byUsernameAndVisible = profileRepository.findByUsernameAndVisible(username, true);
        if (byUsernameAndVisible.isPresent()) {
            throw new AppBadException(resourceBundleService.getMessage("there.is.a.user.with.this.username", language));
        }
    }
}
