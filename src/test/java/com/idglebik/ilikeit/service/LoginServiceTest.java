package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.LoginConverter;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    LoginService loginService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    LoginRepository loginRepository;
    @Autowired
    LoginConverter loginConverter;
    @Autowired
    BCryptPasswordEncoderImpl bCryptPasswordEncoder;

    @Test
    public void signUp() {
        final LoginDto loginDto = MockData.loginDto();

        doReturn(null).when(loginRepository).findByUsername(loginDto.getUsername());
        doReturn(MockData.loginDboAdmin()).when(loginRepository).save(any(LoginDbo.class));

        final ResponseEntity<Response<LoginDto>> responseUserLogin = loginService.signUp(MockData.loginDto());

        assertNotNull(responseUserLogin.getBody().getData());
        assertEquals(responseUserLogin.getStatusCode(), HttpStatus.OK);
        assertEquals(loginDto.getUsername(), responseUserLogin.getBody().getData().getUsername());
        verify(loginRepository, times(1)).save(any(LoginDbo.class));
        verify(loginRepository, times(1)).findByUsername(loginDto.getUsername());
    }

    @Test
    public void isCanCreateAccount() {
        doReturn(MockData.loginDboAdmin()).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(new ArrayList<UserDbo>()).when(userRepository).findByLoginDbo(any(LoginDbo.class));

        boolean canCreateAccount = loginService.isCanCreateAccount(MockData.getAuthentication());

        assertTrue(canCreateAccount);
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findByLoginDbo(any(LoginDbo.class));
    }

    @Test
    public void isUserHavePermission() {
        doReturn(MockData.loginDboAdmin()).when(loginRepository).findByUsername(MockData.getAuthentication().getName());

        boolean userHavePermission = loginService.isUserHavePermission(MockData.getAuthentication());

        assertTrue(userHavePermission);
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
    }

    @TestConfiguration
    public static class LoginServiceTestConfiguration {
        @Bean
        LoginService loginService(final LoginRepository loginRepository,
                                  final LoginConverter loginConverter,
                                  final BCryptPasswordEncoderImpl bCryptPasswordEncoder,
                                  final UserRepository userRepository) {
            return new LoginService(loginRepository, loginConverter, bCryptPasswordEncoder, userRepository);
        }

        @Bean
        public LoginConverter loginConverter() {
            return new LoginConverter();
        }

        @Bean
        public BCryptPasswordEncoderImpl bCryptPasswordEncoder() {
            return new BCryptPasswordEncoderImpl();
        }
    }
}
