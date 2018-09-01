package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.service.UserService;
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

    @ApiOperation("delete user")
    @DeleteMapping("/delete")
    @ResponseBody
    public String deleteUser( @RequestParam Long userID) {
       return   userService.deleteUser(userID);

    }

    @ApiOperation("update user")
    @PutMapping("/update")
    @ResponseBody
    public UserDto updateUSer(@RequestParam Long userID, @RequestBody UserDto userDto) {
        return   userService.updateUser(userID, userDto);
    }
}
