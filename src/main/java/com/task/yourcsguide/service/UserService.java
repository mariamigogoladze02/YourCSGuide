package com.task.yourcsguide.service;

import com.task.yourcsguide.entity.UserCreateUpdateDTO;
import com.task.yourcsguide.entity.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> search(String username, Pageable pageable);

    UserDTO create(UserCreateUpdateDTO dto);

    UserDTO update(UserCreateUpdateDTO dto);

    UserDTO delete(Long id);
}
