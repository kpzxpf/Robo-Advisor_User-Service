package com.vlz.userservice.dto.event;

import com.vlz.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByUsernameReply {
    private User user;
    private boolean success;
    private String message;
}