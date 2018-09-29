package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.LoginConverter;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import com.idglebik.ilikeit.enumerated.Role;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final LoginConverter loginConverter;
    private final BCryptPasswordEncoderImpl bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public ResponseEntity<Response<LoginDbo>> signUp(LoginDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (loginRepository.findByUsername(user.getUsername()) == null) {
            user.setRoles(Collections.singleton(Role.USER));
            LoginDbo dbo = loginRepository.save(loginConverter.convertToDbo(user));
            return ResponseEntity.ok(Response.success(dbo));
        } else {
            return new ResponseEntity(Response.error("A user with this name already exists"), HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isCanCreateAccount(Authentication authentication) {
        final LoginDbo login = loginRepository.findByUsername(authentication.getName());
        return userRepository.findByLoginDbo(login).size() == 0 || login.getRoles().contains(Role.ADMIN);
    }

    public boolean isUserHavePermission(Authentication auth) {
        final LoginDbo login = loginRepository.findByUsername(auth.getName());
        return login.getRoles().contains(Role.ADMIN);
    }
}
