package com.fitness.userServices.controller;

import com.fitness.userServices.dto.RegisterUserRequest;
import com.fitness.userServices.dto.UserResponse;
import com.fitness.userServices.model.User;
import com.fitness.userServices.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public  ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        System.out.println("User request Comes "+userId);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request){
      log.info("Controller Cheking keycloak : {}",request);
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @GetMapping("/{userId}/validate")
    public  ResponseEntity<Boolean> validateUserId(@PathVariable String userId){
        return ResponseEntity.ok(userService.existByUserKeycloakId(userId));
    }

}
