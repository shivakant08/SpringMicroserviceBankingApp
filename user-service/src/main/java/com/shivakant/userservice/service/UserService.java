package com.shivakant.userservice.service;

import com.netflix.discovery.converters.Auto;
import com.shivakant.userservice.entity.User;
import com.shivakant.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);
}
