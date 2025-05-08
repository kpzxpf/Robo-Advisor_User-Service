package com.vlz.userservice.controller;

import com.vlz.userservice.dto.UserDto;
import com.vlz.userservice.mapper.UserMapper;
import com.vlz.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userMapper.toDto(
                userService.createUser(userDto));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userMapper.toDto(
                userService.updateUser(userDto));
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userMapper.toDto(
                userService.getUserById(id));
    }

}
