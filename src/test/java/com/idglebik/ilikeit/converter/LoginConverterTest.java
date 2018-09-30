package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginConverterTest {

    private LoginConverter loginConverter;

    @Test
    public void convertToDto() {
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final LoginDto loginDto = loginConverter.convertToDto(loginDbo);
        assertEquals(loginDbo.getUsername(), loginDto.getUsername());
        assertEquals(loginDbo.getPassword(), loginDto.getPassword());
        assertEquals(loginDbo.getRoles(), loginDto.getRoles());
    }

    @Test
    public void convertToDbo() {
        final LoginDto loginDto = MockData.loginDto();
        final LoginDbo loginDbo = loginConverter.convertToDbo(loginDto);
        assertEquals(loginDto.getUsername(), loginDbo.getUsername());
        assertEquals(loginDto.getPassword(), loginDbo.getPassword());
        assertEquals(loginDto.getRoles(), loginDbo.getRoles());

    }

    @Before
    public void setLoginConverter() {
        loginConverter = new LoginConverter();
    }
}
