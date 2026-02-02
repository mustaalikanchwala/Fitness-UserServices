package com.fitness.userServices.services;

import com.fitness.userServices.dto.RegisterUserRequest;
import com.fitness.userServices.dto.UserResponse;
import com.fitness.userServices.exceptions.AlreadyRgisterUserException;
import com.fitness.userServices.exceptions.UserNotFoundException;
import com.fitness.userServices.model.User;
import com.fitness.userServices.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(@Valid RegisterUserRequest request) {

        if(userRepository.existsByEmail(request.email())){
            throw new AlreadyRgisterUserException("User Already Exsists");
        }

        User user = User.builder()
                .last_name(request.lastname())
                .email(request.email())
                .first_name(request.firstname())
                .password(request.password())
                .build();
        return userRepository.save(user);
    }

    public UserResponse getUserById(Long userId) {
        return UserResponse.response(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    public Boolean existByUserId(Long userId) {
        return userRepository.existsById(userId);
    }
}
