package com.amalitech.techBuddy.service;

import com.amalitech.techBuddy.dto.UserDto;
import com.amalitech.techBuddy.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public String registerUser(UserDto userDto);
}
