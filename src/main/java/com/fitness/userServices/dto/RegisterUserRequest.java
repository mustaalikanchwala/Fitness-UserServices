package com.fitness.userServices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @Email(message = "Invalid Email Format")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8,message = "Password must have AtLeast 8 Character")
        String password,

        @NotBlank(message = "First Name is required")
        String firstname,

        @NotBlank(message = "Last Name is required")
        String lastname
) {}
