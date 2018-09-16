package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.LoginConverter;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.idglebik.ilikeit.constant.SecurityConstants.ADMIN_LOGIN;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final LoginConverter loginConverter;
    private final BCryptPasswordEncoderImpl bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public ResponseEntity<Response<String>> signUp(LoginDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (loginRepository.findByUsername(user.getUsername()) == null) {
            loginRepository.save(loginConverter.convertToDbo(user));
            return ResponseEntity.ok(Response.success("User was created"));
        } else {
            return new ResponseEntity(Response.error("A user with this name already exists"), HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isCanCreateAccount(Authentication authentication){
        final LoginDbo login = loginRepository.findByUsername(authentication.getName());
        return userRepository.findByLoginDbo(login) == null || authentication.getName().equals(ADMIN_LOGIN);
    }
}
