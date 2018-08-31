package com.idglebik.ilekeit;

import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;

public class MockData {

    public static UserDbo personDbo() {
        UserDbo userDbo = new UserDbo();
        userDbo.setFirstName("userDbo first name");
        userDbo.setLastName("userDbo last name");
        userDbo.setAge(23);
    /*    Set set2 = new HashSet();
        set2.add(Position.manager);
        userDbo.setPosition(set2);*/
      /*  Set set = new HashSet();
        set.add(Sex.female);
        userDbo.setSex(set);*/
        return userDbo;
    }

    public static UserDto personDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("userDto first name");
        userDto.setLastName("userDto last name");
        userDto.setAge(23);
       /* Set set2 = new HashSet();
        set2.add(Position.manager);
        userDto.setPosition(set2);*/
/*        Set set = new HashSet();
        set.add(Sex.female);
        userDto.setSex(set);*/
        return userDto;
    }
}
