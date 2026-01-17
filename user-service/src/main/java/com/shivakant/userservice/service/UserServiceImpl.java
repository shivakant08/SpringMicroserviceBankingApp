package com.shivakant.userservice.service;

import com.shivakant.userservice.dto.AuthResponseDto;
import com.shivakant.userservice.dto.LoginRequestDto;
import com.shivakant.userservice.dto.UserRequestDto;
import com.shivakant.userservice.dto.UserResponseDto;
import com.shivakant.userservice.entity.Role;
import com.shivakant.userservice.entity.User;
import com.shivakant.userservice.exception.InvalidCredentialsException;
import com.shivakant.userservice.exception.UserAlreadyExistsException;
import com.shivakant.userservice.exception.UserNotFoundException;
import com.shivakant.userservice.repository.UserRepository;
import com.shivakant.userservice.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto){
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email already registered");
        }
       User user = new User(
               dto.getName(),
               dto.getEmail(),
               passwordEncoder.encode(dto.getPassword()),
               dto.getPhone(),
               Role.USER
       );
       User saved = userRepository.save(user);
       return mapToResponse(saved);

    }

    @Override
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream().map(this::mapToResponse).toList();

    }

    @Override
    public UserResponseDto getUserById(Long id){
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id:" + id));
        return mapToResponse(user);
    }

//    @Override
//    public AuthResponseDto login(LoginRequestDto dto){
//        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));
//
//        if (!passwordEncoder.matches( dto.getPassword(), user.getPassword())){
//            throw new InvalidCredentialsException("Invalid credentials");
//
//        }
//
//        String token = jwtUtil.generateToken(dto.getEmail());
//        return new AuthResponseDto(token);
//    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDto(token);
    }


    private UserResponseDto mapToResponse(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name()
        );
    }
}
