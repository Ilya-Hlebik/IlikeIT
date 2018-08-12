package com.wgdetective.projectstartdemo.controller;

import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody final UserDto userDto){
        userService.createUser(userDto);
        return "User created";
    }

    @GetMapping("/list")
    public List<UserDto> getAllPersons() {
        return userService.getUserList();
    }

}
