package com.task.yourcsguide.service;

import com.task.yourcsguide.entity.dto.UserCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> search(String username, Pageable pageable);

    UserDTO create(UserCreateUpdateDTO dto);

    UserDTO update(UserCreateUpdateDTO dto);

    UserDTO delete(Long id);
}
