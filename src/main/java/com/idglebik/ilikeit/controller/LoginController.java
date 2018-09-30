package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import com.idglebik.ilikeit.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/sign-up")
    public ResponseEntity<Response<LoginDto>> signUp(@RequestBody @Valid LoginDto loginDto) {
        return loginService.signUp(loginDto);
    }
}