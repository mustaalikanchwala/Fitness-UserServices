package com.fitness.userServices.controller;

import com.fitness.userServices.dto.RegisterUserRequest;
import com.fitness.userServices.dto.UserResponse;
import com.fitness.userServices.model.User;
import com.fitness.userServices.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public  ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request){
        User user = userService.registerUser(request);
        return ResponseEntity.ok(UserResponse.response(user));
    }

}
