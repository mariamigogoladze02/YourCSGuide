package com.task.yourcsguide.service.impl;

import com.task.yourcsguide.entity.Profile;
import com.task.yourcsguide.entity.dto.ProfileCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.ProfileDTO;
import com.task.yourcsguide.repository.ProfileRepository;
import com.task.yourcsguide.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Page<ProfileDTO> search(String name, Pageable pageable) {
        Page<Profile> profiles;

        if (name == null || name.isBlank()) {
            profiles = profileRepository.findAll(pageable);
        } else {
            profiles = profileRepository.findByNameContainingIgnoreCase(name, pageable);
        }

        return profiles.map(this::toDTO);
    }

    @Override
    public ProfileDTO create(ProfileCreateUpdateDTO dto) {
        Profile profile = new Profile();

        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setDescription(dto.getDescription());
        profile.setExperience(dto.getExperience());
        profile.setSkills(dto.getSkills());
        profile.setEmail(dto.getEmail());

        return toDTO(profileRepository.save(profile));
    }

    @Override
    public ProfileDTO update(ProfileCreateUpdateDTO dto) {
        Profile profile = profileRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setDescription(dto.getDescription());
        profile.setExperience(dto.getExperience());
        profile.setSkills(dto.getSkills());
        profile.setEmail(dto.getEmail());

        return toDTO(profileRepository.save(profile));
    }

    @Override
    public ProfileDTO delete(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profileRepository.delete(profile);
        return toDTO(profile);
    }

    private ProfileDTO toDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setDescription(profile.getDescription());
        dto.setExperience(profile.getExperience());
        dto.setSkills(profile.getSkills());
        dto.setEmail(profile.getEmail());

        return dto;
    }
}
