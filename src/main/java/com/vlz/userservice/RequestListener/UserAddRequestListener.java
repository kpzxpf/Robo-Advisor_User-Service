package com.vlz.userservice.RequestListener;

import com.vlz.userservice.dto.UserDto;
import com.vlz.userservice.dto.event.UserAddEvent;
import com.vlz.userservice.dto.event.UserSavedEvent;
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
public class UserAddRequestListener {
    private final UserService userService;

    @KafkaListener(topics = "${spring.kafka.topic.names.user-add-request-topic}")
    @SendTo("${spring.kafka.topic.names.user-add-reply-topic}")
    public UserSavedEvent listenUserAddRequest(UserAddEvent userAddEvent) {
        try {
            User savedUser = userService.save(userAddEvent);
            UserSavedEvent replyEvent = new UserSavedEvent(
                    UserDto.builder()
                            .id(savedUser.getId())
                            .username(savedUser.getUsername())
                            .email(savedUser.getEmail())
                            .build(),
                    true,
                    "User saved successfully"
            );
            log.info("Sending user add reply: {}", replyEvent);
            return replyEvent;
        } catch (Exception e) {
            log.error("Failed to save user on topic: {}", e.getMessage());
            return new UserSavedEvent(null, false, "Failed to save user: " + e.getMessage());
        }
    }
}
