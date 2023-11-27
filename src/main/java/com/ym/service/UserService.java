package com.ym.service;

import com.ym.dto.UserDto;
import com.ym.mapper.UserMapper;
import com.ym.model.AppUser;
import com.ym.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserDto addUser(UserDto userDto){
        AppUser appUser = userMapper.toAppUser(userDto);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
        return userMapper.toUserDto(appUser);
    }
}
