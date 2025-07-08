package com.task.yourcsguide.service;

import com.task.yourcsguide.entity.dto.ProfileCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.ProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    Page<ProfileDTO> search(String name, Pageable pageable);

    ProfileDTO create(ProfileCreateUpdateDTO dto);

    ProfileDTO update(ProfileCreateUpdateDTO dto);

    ProfileDTO delete(Long id);
}
