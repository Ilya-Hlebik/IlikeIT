package com.idglebik.ilikeit;

import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.*;
import com.idglebik.ilikeit.enumerated.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class MockData {

    private static final long ID1 = 5L;
    private static final String TEST_INSTITUTION = "testInstitution";
    private static final String TEST = "test";
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";
    private static final String ADDRESS = "address";
    private static final String GENERAL_INFO = "generalInfo";
    private static final String OTHER_INFO = "otherInfo";
    private static final long ID = 1L;
    private static final long FRIEND_ID = 2L;
    private static final long FRIEND_ID1 = 3L;
    private static final int AGE = 24;
    public static final String TEST_ID = "testId";

    @Bean
    public static BCryptPasswordEncoderImpl bCryptPasswordEncoder() {
        return new BCryptPasswordEncoderImpl();
    }


    public static UserDbo userDbo() {
        final UserDbo userDbo = new UserDbo();
        userDbo.setId(ID1);
        userDbo.setFirstName(FIRST);
        userDbo.setLastName(LAST);
        userDbo.setAge(AGE);
        userDbo.setSexes(Collections.singleton(Sex.MALE));
        userDbo.setCity(CITY);
        userDbo.setCountry(COUNTRY);
        userDbo.setAddress(ADDRESS);
        userDbo.setGeneralInfo(GENERAL_INFO);
        userDbo.setOtherInfo(OTHER_INFO);
        userDbo.setPositions(new HashSet<>(Collections.singletonList(new PositionDbo(ID, Position.WORKER, Collections.singleton(userDbo)))));
        userDbo.setStudies(new HashSet<>(Collections.singletonList(new StudyDbo(ID, TEST, userDbo))));
        userDbo.setLanguages(new HashSet<>(Collections.singletonList(new LangDbo(ID, Language.EN, Collections.singleton(userDbo)))));
        userDbo.setLikes(new HashSet<>(Collections.singletonList(new LikeDbo(ID, Like.BOOKS, Collections.singleton(userDbo)))));
        userDbo.setHates(new HashSet<>(Collections.singletonList(new HateDbo(ID, Hate.BOOKS, Collections.singleton(userDbo)))));
        userDbo.setLifePosition(new LifePositionDbo(ID, Aligment.CATOLIC, MainInLife.CAREER, MainInPeople.BRAIN, userDbo));
        userDbo.setLoginDbo(loginDboAdmin());
        userDbo.setFriendDbos(new HashSet<>(Arrays.asList(new FriendDbo(ID, FRIEND_ID, userDbo), new FriendDbo(FRIEND_ID, FRIEND_ID1, userDbo))));
        return userDbo;
    }

    public static UserDto userDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST);
        userDto.setLastName(LAST);
        userDto.setAge(AGE);
        userDto.setSexes(Collections.singleton(Sex.MALE));
        userDto.setCity(CITY);
        userDto.setCountry(COUNTRY);
        userDto.setAddress(ADDRESS);
        userDto.setGeneralInfo(GENERAL_INFO);
        userDto.setOtherInfo(OTHER_INFO);
        userDto.setPositions(new HashSet<>(Collections.singletonList(new PositionDto(Position.WORKER))));
        userDto.setStudies(new HashSet<>(Collections.singletonList(new StudyDto(TEST))));
        userDto.setLanguages(new HashSet<>(Collections.singletonList(new LangDto(Language.EN))));
        userDto.setLikes(new HashSet<>(Collections.singletonList(new LikeDto(Like.BOOKS))));
        userDto.setHates(new HashSet<>(Collections.singletonList(new HateDto(Hate.BOOKS))));
        userDto.setLifePosition(new LifePositionDto(Aligment.CATOLIC, MainInLife.CAREER, MainInPeople.BRAIN));
        userDto.setFriendDtos(new HashSet<>(Arrays.asList(new FriendDto(FRIEND_ID), new FriendDto(FRIEND_ID1))));
        return userDto;
    }

    public static LoginDbo loginDboAdmin() {
        final BCryptPasswordEncoderImpl bCryptPasswordEncoder = bCryptPasswordEncoder();
        final LoginDbo loginDbo = new LoginDbo();
        loginDbo.setUsername(TEST);
        loginDbo.setPassword(bCryptPasswordEncoder.encode(TEST));
        loginDbo.setRoles(Collections.singleton(Role.ADMIN));
        return loginDbo;
    }

    public static LoginDto loginDto() {
        final BCryptPasswordEncoderImpl bCryptPasswordEncoder = bCryptPasswordEncoder();
        final LoginDto loginDto = new LoginDto();
        loginDto.setUsername(TEST);
        loginDto.setPassword(bCryptPasswordEncoder.encode(TEST));
        loginDto.setRoles(Collections.singleton(Role.ADMIN));
        return loginDto;
    }

    public static FriendDbo friendDbo(final UserDbo userDbo, long friendId) {
        final FriendDbo friendDbo = new FriendDbo();
        friendDbo.setUserDbo(userDbo);
        friendDbo.setFriendId(friendId);
        return friendDbo;
    }

    public static FriendDbo friendDbo() {
        final FriendDbo friendDbo = new FriendDbo();
        friendDbo.setUserDbo(MockData.userDbo());
        friendDbo.setFriendId(ID);
        return friendDbo;
    }

    public static FriendDto friendDto() {
        final FriendDto friendDto = new FriendDto();
        friendDto.setFriendId(ID);
        return friendDto;
    }

    public static PositionDbo positionDbo() {
        final PositionDbo positionDbo = new PositionDbo();
        positionDbo.setPositionName(Position.WORKER);
        positionDbo.setUsers(new HashSet<>(Arrays.asList(MockData.userDbo(), MockData.userDbo())));
        return positionDbo;
    }

    public static PositionDto positionDto() {
        final PositionDto positionDto = new PositionDto();
        positionDto.setPositionName(Position.WORKER);
        return positionDto;
    }

    public static HateDbo hateDbo() {
        final HateDbo hateDbo = new HateDbo();
        hateDbo.setHate(Hate.BOOKS);
        hateDbo.setUsers(new HashSet<>(Arrays.asList(MockData.userDbo(), MockData.userDbo())));
        return hateDbo;
    }

    public static HateDto hateDto() {
        final HateDto hateDto = new HateDto();
        hateDto.setHate(Hate.BOOKS);
        return hateDto;
    }

    public static LikeDbo likeDbo() {
        final LikeDbo likeDbo = new LikeDbo();
        likeDbo.setLike(Like.BOOKS);
        likeDbo.setUsers(new HashSet<>(Arrays.asList(MockData.userDbo(), MockData.userDbo())));
        return likeDbo;
    }

    public static LikeDto likeDto() {
        final LikeDto likeDto = new LikeDto();
        likeDto.setLike(Like.BOOKS);
        return likeDto;
    }

    public static LangDbo langDbo() {
        final LangDbo langDbo = new LangDbo();
        langDbo.setLanguage(Language.EN);
        langDbo.setUsers(new HashSet<>(Arrays.asList(MockData.userDbo(), MockData.userDbo())));
        return langDbo;
    }

    public static LangDto langDto() {
        final LangDto langDto = new LangDto();
        langDto.setLanguage(Language.EN);
        return langDto;
    }

    public static LifePositionDto lifePositionDto() {
        final LifePositionDto lifePositionDto = new LifePositionDto();
        lifePositionDto.setAligment(Aligment.CATOLIC);
        lifePositionDto.setMainInLife(MainInLife.CAREER);
        lifePositionDto.setMainInPeople(MainInPeople.BRAIN);
        return lifePositionDto;
    }

    public static LifePositionDbo lifePositionDbo() {
        final LifePositionDbo lifePositionDbo = new LifePositionDbo();
        lifePositionDbo.setAligment(Aligment.CATOLIC);
        lifePositionDbo.setMainInLife(MainInLife.CAREER);
        lifePositionDbo.setMainInPeople(MainInPeople.BRAIN);
        return lifePositionDbo;
    }

    public static ChatMessageDbo chatMessageDbo() {
        final ChatMessageDbo chatMessageDbo = new ChatMessageDbo();
        chatMessageDbo.setContent(TEST);
        chatMessageDbo.setSender(ID);
        chatMessageDbo.setRecipient(FRIEND_ID);
        chatMessageDbo.setDate(gedTestDate());
        chatMessageDbo.setHistoryId(TEST_ID);
        return chatMessageDbo;
    }

    public static ChatMessageDto chatMessageDto() {
        final ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setContent(TEST);
        chatMessageDto.setSender(ID);
        chatMessageDto.setRecipient(FRIEND_ID);
        return chatMessageDto;
    }

    public static StudyDbo studyDbo() {
        final StudyDbo studyDbo = new StudyDbo();
        studyDbo.setInstitution(TEST_INSTITUTION);
        studyDbo.setUserDbo(userDbo());
        return studyDbo;
    }

    public static StudyDto studyDto() {
        final StudyDto studyDto = new StudyDto();
        studyDto.setInstitution(TEST_INSTITUTION);
        return studyDto;
    }

    private static Date gedTestDate() {
        Date date = new Date();
        date.setTime(10000);
        return date;
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
