package com.wgdetective.projectstartdemo.service;

import com.wgdetective.projectstartdemo.converter.UserConverter;
import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(final UserRepository userRepository, final UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter= userConverter;
    }

    public void createUser(final UserDto userDto){
        userRepository.save(userConverter.convertToDbo(userDto));
    }

    public List<UserDto> getUserList(){
        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }
}
