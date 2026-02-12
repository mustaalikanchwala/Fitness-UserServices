package com.fitness.userServices.services;

import com.fitness.userServices.dto.RegisterUserRequest;
import com.fitness.userServices.dto.UserResponse;
import com.fitness.userServices.exceptions.AlreadyRgisterUserException;
import com.fitness.userServices.exceptions.UserNotFoundException;
import com.fitness.userServices.model.User;
import com.fitness.userServices.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserResponse registerUser(@Valid RegisterUserRequest request) {
      log.info("Registering User : {}",request);
        if(userRepository.existsByEmail(request.email())){
            User existingUser = userRepository.findByEmail(request.email());
            return UserResponse.response(existingUser);
        }

        User user = User.builder()
                .last_name(request.lastname())
                .email(request.email())
                .keycloakId(request.keycloakId())
                .first_name(request.firstname())
                .password(request.password())
                .build();

        return UserResponse.response(userRepository.save(user));
    }

    public UserResponse getUserById(Long userId) {
        return UserResponse.response(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    public Boolean existByUserKeycloakId(String userId) {
        log.info("Validating User : {}",userId);
        return userRepository.existsByKeycloakId(userId);
    }
}
