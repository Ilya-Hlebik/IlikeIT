package com.wgdetective.projectstartdemo.controller;

import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("RestController")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation("create user")
    @PostMapping("/create")
    @ResponseBody
    public String createUser(@RequestBody final UserDto userDto) {
        userService.createUser(userDto);
        return "User created";
    }

    @ApiOperation("show list of users")
    @GetMapping("/list")
    @ResponseBody
    public List<UserDto> getAllPersons() {
        return userService.getUserList();
    }

}
