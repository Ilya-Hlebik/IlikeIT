package com.idglebik.ilikeit;

import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.*;
import com.idglebik.ilikeit.enumerated.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class MockData {
    @Bean
    public static BCryptPasswordEncoderImpl bCryptPasswordEncoder() {
        return new BCryptPasswordEncoderImpl();
    }


    public static UserDbo userDbo() {
        final UserDbo userDbo = new UserDbo();
        userDbo.setId(5L);
        userDbo.setFirstName("first");
        userDbo.setLastName("last");
        userDbo.setAge(24);
        userDbo.setSexes(Collections.singleton(Sex.MALE));
        userDbo.setCity("city");
        userDbo.setCountry("country");
        userDbo.setAddress("address");
        userDbo.setGeneralInfo("generalInfo");
        userDbo.setOtherInfo("otherInfo");
        userDbo.setPositions(new HashSet<>(Collections.singletonList(new PositionDbo(1L, Position.WORKER, Collections.singleton(userDbo)))));
        userDbo.setStudies(new HashSet<>(Collections.singletonList(new StudyDbo(1L, "test", userDbo))));
        userDbo.setLanguages(new HashSet<>(Collections.singletonList(new LangDbo(1L, Language.EN, Collections.singleton(userDbo)))));
        userDbo.setLikes(new HashSet<>(Collections.singletonList(new LikeDbo(1L, Like.BOOKS, Collections.singleton(userDbo)))));
        userDbo.setHates(new HashSet<>(Collections.singletonList(new HateDbo(1L, Hate.BOOKS, Collections.singleton(userDbo)))));
        userDbo.setLIfePosition(new LifePositionDbo(1L, Aligment.CATOLIC, MainInLife.CAREER, MainInPeople.BRAIN, userDbo));
        userDbo.setLoginDbo(loginDboAdmin());
        userDbo.setFriendDbos(new HashSet<>(Arrays.asList(new FriendDbo(1L, 2L, userDbo), new FriendDbo(2L, 3L, userDbo))));
        return userDbo;
    }

    public static LoginDbo loginDboAdmin() {
        final BCryptPasswordEncoderImpl bCryptPasswordEncoder = bCryptPasswordEncoder();
        final LoginDbo loginDbo = new LoginDbo();
        loginDbo.setUsername("test");
        loginDbo.setPassword(bCryptPasswordEncoder.encode("test"));
        loginDbo.setRoles(Collections.singleton(Role.ADMIN));
        return loginDbo;
    }

    public static LoginDto loginDto() {
        final BCryptPasswordEncoderImpl bCryptPasswordEncoder = bCryptPasswordEncoder();
        final LoginDto loginDto = new LoginDto();
        loginDto.setUsername("test");
        loginDto.setPassword(bCryptPasswordEncoder.encode("test"));
        loginDto.setRoles(Collections.singleton(Role.ADMIN));
        return loginDto;
    }

    public static UserDto userDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("first");
        userDto.setLastName("last");
        userDto.setAge(24);
        userDto.setSexes(Collections.singleton(Sex.MALE));
        userDto.setCity("city");
        userDto.setCountry("country");
        userDto.setAddress("address");
        userDto.setGeneralInfo("generalInfo");
        userDto.setOtherInfo("otherInfo");
        userDto.setPositions(new HashSet<>(Collections.singletonList(new PositionDto(Position.WORKER))));
        userDto.setStudies(new HashSet<>(Collections.singletonList(new StudyDto("test"))));
        userDto.setLanguages(new HashSet<>(Collections.singletonList(new LangDto(Language.EN))));
        userDto.setLikes(new HashSet<>(Collections.singletonList(new LikeDto(Like.BOOKS))));
        userDto.setHates(new HashSet<>(Collections.singletonList(new HateDto(Hate.BOOKS))));
        userDto.setLifePosition(new LifePositionDto(Aligment.CATOLIC, MainInLife.CAREER, MainInPeople.BRAIN));
        userDto.setFriendDtos(new HashSet<>(Arrays.asList(new FriendDto(2L), new FriendDto(3L))));
        return userDto;
    }

    public static FriendDbo friendDbo(final UserDbo userDbo, long friendId) {
        final FriendDbo friendDbo = new FriendDbo();
        friendDbo.setUserDbo(userDbo);
        friendDbo.setFriendId(friendId);
        return friendDbo;
    }

    public static Authentication getAuthentication() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }
}
