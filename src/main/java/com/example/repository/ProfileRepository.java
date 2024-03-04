package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByUsernameAndVisible(String username, Boolean visible);

}
