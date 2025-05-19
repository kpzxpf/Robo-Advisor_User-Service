package com.vlz.userservice.RequestListener;

import com.vlz.userservice.dto.event.FindUserByUsernameReply;
import com.vlz.userservice.dto.event.FindUserByUsernameRequest;
import com.vlz.userservice.entity.User;
import com.vlz.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFindByIdRequestListener {
    private final UserService userService;

    @KafkaListener(topics = "${spring.kafka.topic.names.user-find-by-username-request-topic}")
    @SendTo("${spring.kafka.topic.names.user-find-by-username-reply-topic}")
    public FindUserByUsernameReply listenUserFindByUsernameRequest(FindUserByUsernameRequest findUserByUsernameRequest) {
        log.info("Received find user by username request on topic {}:", findUserByUsernameRequest);
        User foundUser = userService.getUserByUsername(findUserByUsernameRequest.getUsername());
        FindUserByUsernameReply reply;
        if (foundUser != null) {
            reply = FindUserByUsernameReply.builder()
                    .user(User.builder()
                            .id(foundUser.getId())
                            .username(foundUser.getUsername())
                            .email(foundUser.getEmail())
                            .build())
                    .success(true)
                    .message("User found")
                    .build();
            log.info("User found, sending reply: {}", reply);
        } else {
            reply = FindUserByUsernameReply.builder()
                    .user(null)
                    .success(false)
                    .message("User not found for username: " + findUserByUsernameRequest.getUsername())
                    .build();
            log.info("User not found for username: {}", findUserByUsernameRequest.getUsername());
        }
        return reply;
    }
}
