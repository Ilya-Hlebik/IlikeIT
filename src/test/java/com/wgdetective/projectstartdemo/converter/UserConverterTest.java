package com.wgdetective.projectstartdemo.converter;

import com.wgdetective.projectstartdemo.MockData;
import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.dto.UserDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserConverterTest {
    private final UserConverter userConverter = new UserConverter();

    @Test
    public void convertToDto(){
        final UserDbo userDbo = MockData.personDbo();
        final UserDto userDto = userConverter.convertToDto(userDbo);
        assertEquals(userDbo.getFirstName(), userDto.getFirstName());
        assertEquals(userDbo.getLastName(), userDto.getLastName());
        assertEquals(userDbo.getAge(), userDto.getAge());
        assertEquals(userDbo.getSex(), userDto.getSex());
        assertEquals(userDbo.getPosition(), userDto.getPosition());
    }

    @Test
    public void convertToDbo() {
        final UserDto userDto = MockData.personDto();
        final UserDbo userDbo = userConverter.convertToDbo(userDto);
        assertEquals(userDto.getFirstName(), userDbo.getFirstName());
        assertEquals(userDto.getLastName(), userDbo.getLastName());
        assertEquals(userDto.getAge(), userDbo.getAge());
        assertEquals(userDto.getSex(), userDbo.getSex());
        assertEquals(userDto.getPosition(), userDbo.getPosition());
    }
}
