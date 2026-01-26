package com.fitness.userServices.dto;

import com.fitness.userServices.model.User;
import lombok.Builder;


import java.time.LocalDateTime;
@Builder
public record UserResponse(
        Long user_id,
        String email,
        String first_name,
        String last_name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public static UserResponse response(User user){
        return UserResponse.builder()
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .user_id(user.getUserId())
                .email(user.getEmail())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .build();
    }
}
