package com.task.yourcsguide.service;

import com.task.yourcsguide.entity.User;
import com.task.yourcsguide.entity.UserCreateUpdateDTO;
import com.task.yourcsguide.entity.UserDTO;
import com.task.yourcsguide.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDTO> search(String username, Pageable pageable) {
        Page<User> users;

        if (username == null || username.isBlank()) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
        }

        return users.map(this::toDTO);
    }

    @Override
    public UserDTO create(UserCreateUpdateDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔐 ENCRYPTED
        user.setRole(dto.getRole());

        return toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserCreateUpdateDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("User ID is required for update.");
        }

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());

        // Optionally re-encode password only if updated
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        user.setRole(dto.getRole());

        return toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
        return toDTO(user);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
