package com.idglebik.ilekeit.service;

import com.idglebik.ilikeit.service.UserService;
import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.enumerated.Sex;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Spy
    private UserConverter userConverter;

    @Test
    public void createPerson() {
        final UserDbo personDbo = new UserDbo();
        personDbo.setFirstName("first");
        personDbo.setLastName("last");
        personDbo.setAge(24);
        Set<Sex> set = new HashSet<>();
        set.add(Sex.male);
   /*     Set<Position> set2 = new HashSet<>();
        set2.add(Position.developer);
        personDbo.setSex(set);*/
        /*personDbo.setPosition(set2);*/

        doReturn(personDbo).when(userRepository).save(any(UserDbo.class));

        userService.createUser(new UserDto());

        verify(userRepository, times(1)).save(any(UserDbo.class));
    }

/*    @Test
    public void getPersonsList() {
        final List<UserDbo> findAllResult = new ArrayList<>();
        final UserDbo userDbo = MockData.personDbo();
        findAllResult.add(userDbo);
        findAllResult.add(userDbo);
        doReturn(findAllResult).when(userRepository).findAll();

        final List<UserDto> userList = userService.getUserList();

        verify(userRepository, times(1)).findAll();
        assertEquals(findAllResult.size(), userList.size());
        for (final UserDto personDto : userList) {
            assertEquals(userDbo.getFirstName(), personDto.getFirstName());
            assertEquals(userDbo.getLastName(), personDto.getLastName());
        }

    }*/
}
