package com.vlz.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
}
