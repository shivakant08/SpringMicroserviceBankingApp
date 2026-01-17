package com.shivakant.userservice.service;

import com.netflix.discovery.converters.Auto;
import com.shivakant.userservice.dto.AuthResponseDto;
import com.shivakant.userservice.dto.LoginRequestDto;
import com.shivakant.userservice.dto.UserRequestDto;
import com.shivakant.userservice.dto.UserResponseDto;
import com.shivakant.userservice.entity.User;
import com.shivakant.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {


    UserResponseDto createUser(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    AuthResponseDto login(LoginRequestDto dto);
}
