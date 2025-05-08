package com.vlz.userservice.service;

import com.vlz.userservice.dto.UserDto;
import com.vlz.userservice.entity.User;
import com.vlz.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User updateUser(UserDto userDto) {
        User user = getUserById(userDto.getId());

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("UserService getUserById(): User with id {} not found", id);
                    return new EntityNotFoundException("User with id " + id + " not found");
                });
    }
}
