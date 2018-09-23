package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.exception.CantSaveUserException;
import com.idglebik.ilikeit.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("RestController")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("create user")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Response<UserDto>> createUser(@RequestBody @Valid final UserDto userDto, Authentication auth)
            throws CantSaveUserException {
        return userService.createUser(userDto, auth);
    }

    @ApiOperation("show list of users")
    @GetMapping
    @ResponseBody
    public ResponseEntity<Response<List<UserDto>>> getAllPersons() {
        return userService.getUserList();
    }

    @ApiOperation("show current users")
    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> getUser(Authentication auth) {
        return userService.getCurrentUser(auth);
    }

    @ApiOperation("delete user")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Response<String>> deleteUser(Authentication auth) {
        return userService.deleteUser(auth);
    }

    @ApiOperation("delete user by id")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Response<String>> deleteUserById(@PathVariable Long id, Authentication auth) {
        return userService.deleteUserById(id, auth);
    }

    @ApiOperation("update user")
    @PutMapping
    @ResponseBody
    public ResponseEntity<Response<UserDto>> updateUSer(Authentication auth, @RequestBody UserDto userDto) throws CantSaveUserException {
        return userService.updateUser(auth, userDto);
    }
}
