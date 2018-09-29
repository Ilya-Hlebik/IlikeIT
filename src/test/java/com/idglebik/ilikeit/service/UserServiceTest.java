package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.config.BCryptPasswordEncoderImpl;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.*;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Role;
import com.idglebik.ilikeit.exception.CantSaveUserException;
import com.idglebik.ilikeit.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    public static class UserServiceTestConfiguration {
        @Bean
        UserService userService(final UserRepository userRepository,
                                final UserConverter userConverter,
                                final PositionRepository positionRepository,
                                final StudyConverter studyConverter,
                                final LanguageRepository languageRepository,
                                final LikeRepository likeRepository,
                                final HateRepository hateRepository,
                                final LifePositionConverter lifePositionConverter,
                                final LifePositionRepository lifePositionRepository,
                                final LoginRepository loginRepository,
                                final LoginService loginService,
                                final SearchService searchService,
                                final FriendRepository friendRepository) {
            return new UserService(userRepository, userConverter, positionRepository, studyConverter, languageRepository,
                    likeRepository, hateRepository, lifePositionConverter, lifePositionRepository, loginRepository,
                    loginService, searchService, friendRepository);
        }

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
        public UserConverter userConverter() {
            return new UserConverter();
        }

        @Bean
        public LifePositionConverter lifePositionConverter() {
            return new LifePositionConverter();
        }

        @Bean
        public FriendConverter friendConverter() {
            return new FriendConverter();
        }

        @Bean
        public StudyConverter studyConverter() {
            return new StudyConverter();
        }

        @Bean
        public PositionConverter positionConverter() {
            return new PositionConverter();
        }

        @Bean
        public LikeConverter likeConverter() {
            return new LikeConverter();
        }

        @Bean
        public HateConverter hateConverter() {
            return new HateConverter();
        }

        @Bean
        public LangConverter langConverter() {
            return new LangConverter();
        }
    }

    @MockBean
    private PositionRepository positionRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LanguageRepository languageRepository;

    @MockBean
    private LikeRepository likeRepository;

    @MockBean
    private HateRepository hateRepository;

    @MockBean
    private LifePositionRepository lifePositionRepository;

    @MockBean
    private LoginRepository loginRepository;

    @MockBean
    BCryptPasswordEncoderImpl bCryptPasswordEncoder;

    @Autowired
    LoginService loginService;

    @MockBean
    private SearchService searchService;

    @MockBean
    private FriendRepository friendRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @Test
    public void createUser() throws CantSaveUserException {
        final UserDbo userDbo = MockData.userDbo();
        final UserDto dto = MockData.userDto();
        final LoginDbo loginDbo = new LoginDbo();
        loginDbo.setRoles(Collections.singleton(Role.ADMIN));

        doReturn(Collections.singletonList(userDbo)).when(userRepository).findByLoginDbo(loginDbo);
        doReturn(userDbo).when(userRepository).save(any(UserDbo.class));
        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());

        ResponseEntity<Response<UserDto>> user = userService.createUser(dto, MockData.getAuthentication());

        assertNotNull(user.getBody().getData());
        assertEquals(user.getStatusCode(), HttpStatus.OK);
        assertEquals(user.getBody().getData(), dto);
        verify(userRepository, times(1)).save(any(UserDbo.class));
        verify(userRepository, times(1)).findByLoginDbo(loginDbo);
        verify(loginRepository, times(2)).findByUsername(MockData.getAuthentication().getName());
    }

    @Test
    public void getUserList() {
        final UserDbo userDbo = MockData.userDbo();
        final List<UserDbo> findAllResult = Arrays.asList(userDbo, userDbo);

        doReturn(findAllResult).when(userRepository).findAll();

        final ResponseEntity<Response<List<UserDto>>> userResponseList = userService.getUserList();
        List<UserDto> userList = userResponseList.getBody().getData();

        verify(userRepository, times(1)).findAll();
        assertEquals(findAllResult.size(), userList.size());
        userList.forEach(personDto -> {
            assertEquals(userDbo.getFirstName(), personDto.getFirstName());
            assertEquals(userDbo.getLastName(), personDto.getLastName());
        });
    }

    @Test
    public void deleteUser() {
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbos).when(userRepository).findByLoginDbo(loginDbo);
        doNothing().when(userRepository).deleteByLoginDbo(any(LoginDbo.class));
        userDbos.forEach(userDbo -> doNothing().when(friendRepository).deleteByUserDboId(userDbo.getId()));

        userService.deleteUser(MockData.getAuthentication());

        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findByLoginDbo(loginDbo);
        verify(userRepository, times(1)).deleteByLoginDbo(any(LoginDbo.class));
        verify(friendRepository, times(2)).deleteByUserDboId(anyLong());
    }

    @Test
    public void updateUser() throws CantSaveUserException {
        final UserDbo userDbo = MockData.userDbo();
        userDbo.setFirstName("testFirstName");
        userDbo.setLastName("testLastName");
        final UserDto dto = userConverter.convertToDto(userDbo);
        final LoginDbo loginDbo = new LoginDbo();
        final List<UserDbo> userDbos = Collections.singletonList(MockData.userDbo());

        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbos).when(userRepository).findByLoginDbo(loginDbo);
        doNothing().when(lifePositionRepository).removeByUser(userDbos.get(0));
        doReturn(userDbo).when(userRepository).save(userDbo);

        ResponseEntity<Response<UserDto>> userUpdateResponse = userService.updateUser(MockData.getAuthentication(), dto);
        UserDto userDto = userUpdateResponse.getBody().getData();

        assertNotNull(userDto);
        assertEquals("testFirstName", userDto.getFirstName());
        assertEquals("testLastName", userDto.getLastName());
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findByLoginDbo(loginDbo);
        verify(userRepository, times(1)).save(userDbo);
    }

    @Test
    public void getCurrentUser() {
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbos).when(userRepository).findByLoginDbo(loginDbo);
        userService.getCurrentUser(MockData.getAuthentication());

        assertNotNull(userDbos);
        verify(userRepository, times(1)).findByLoginDbo(loginDbo);
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
    }

    @Test
    public void deleteUserById() {
        Optional<UserDbo> userDbo = Optional.of(MockData.userDbo());

        doReturn(MockData.loginDboAdmin()).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbo).when(userRepository).findById(anyLong());
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUserById(1L, MockData.getAuthentication());
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}
