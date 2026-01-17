package com.shivakant.userservice.controller;

import com.shivakant.userservice.dto.UserRequestDto;
import com.shivakant.userservice.dto.UserResponseDto;
import com.shivakant.userservice.entity.User;
import com.shivakant.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @GetMapping("/me")
    public String me() {
        return "You are authenticated 🎉";
    }

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto dto){
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        return  userService.getUserById(id);
    }

}
