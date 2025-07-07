package com.task.yourcsguide.controller;

import com.task.yourcsguide.entity.dto.UserCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.UserDTO;
import com.task.yourcsguide.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> searchUsers(@RequestParam(value = "username", required = false) String username,
                                                     Pageable pageable) {
        return ResponseEntity.ok(userService.search(username, pageable));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateUpdateDTO dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserCreateUpdateDTO dto) {
        return ResponseEntity.ok(userService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @Autowired
    public void setAppUserService(UserService userService) {
        this.userService = userService;
    }
}
