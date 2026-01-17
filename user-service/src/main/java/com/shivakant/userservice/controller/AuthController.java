package com.shivakant.userservice.controller;

import com.shivakant.userservice.dto.AuthResponseDto;
import com.shivakant.userservice.dto.LoginRequestDto;
import com.shivakant.userservice.dto.UserRequestDto;
import com.shivakant.userservice.dto.UserResponseDto;
import com.shivakant.userservice.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto dto){
       return userService.createUser(dto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody LoginRequestDto dto){
        return userService.login(dto);
    }
}
