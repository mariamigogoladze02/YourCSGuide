package com.task.yourcsguide.controller;

import com.task.yourcsguide.entity.dto.ProfileCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.ProfileDTO;
import com.task.yourcsguide.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/profiles")
public class ProfileController {
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<ProfileDTO>> searchProfiles(@RequestParam(value = "name", required = false) String name,
                                                           Pageable pageable) {
        return ResponseEntity.ok(profileService.search(name, pageable));
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileCreateUpdateDTO dto) {
        return ResponseEntity.ok(profileService.create(dto));
    }

    @PutMapping
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @RequestBody ProfileCreateUpdateDTO dto) {
        return ResponseEntity.ok(profileService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.delete(id));
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
