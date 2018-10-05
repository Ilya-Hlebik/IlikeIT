package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.repository.LoginRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

    @MockBean
    LoginRepository loginRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername() {
        final LoginDbo loginDbo = MockData.loginDboAdmin();

        doReturn(loginDbo).when(loginRepository).findByUsername(loginDbo.getUsername());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDbo.getUsername());

        assertNotNull(userDetails);
        assertEquals(loginDbo.getUsername(), userDetails.getUsername());
        assertEquals(loginDbo.getPassword(), userDetails.getPassword());
        verify(loginRepository, times(1)).findByUsername(loginDbo.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameUsernameNotFoundException() {
        final LoginDbo loginDbo = MockData.loginDboAdmin();

        doReturn(null).when(loginRepository).findByUsername(loginDbo.getUsername());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDbo.getUsername());

        assertNotNull(userDetails);
        assertEquals(loginDbo.getUsername(), userDetails.getUsername());
        assertEquals(loginDbo.getPassword(), userDetails.getPassword());
        verify(loginRepository, times(1)).findByUsername(loginDbo.getUsername());
    }


    @TestConfiguration
    public static class UserDetailsServiceImplTestConfiguration {
        @Bean
        public UserDetailsServiceImpl userDetailsService(final LoginRepository loginRepository) {
            return new UserDetailsServiceImpl(loginRepository);
        }
    }
}
