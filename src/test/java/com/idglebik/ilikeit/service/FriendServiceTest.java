package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.*;
import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.SearchDto;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FriendServiceTest {

    private static final long friendId = 1L;
    private static final long userId = 2L;
    @MockBean
    FriendRepository friendRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    LoginRepository loginRepository;
    @Autowired
    FriendService friendService;
    @Autowired
    SearchService searchService;

    @Test
    public void addFriend() {

        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final UserDbo userDbo = MockData.userDbo();
        final UserDbo friendDbo = MockData.userDbo();
        userDbo.setId(userId);
        friendDbo.setId(friendId);
        final List<UserDbo> findAllResult = Arrays.asList(userDbo, userDbo);
        final FriendDbo friend = MockData.friendDbo(userDbo, friendId);

        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(Optional.of(userDbo)).when(userRepository).findById(userId);
        doReturn(Optional.of(friendDbo)).when(userRepository).findById(friendId);
        doReturn(findAllResult).when(userRepository).findByLoginDboUsername(MockData.getAuthentication().getName());
        doReturn(Optional.empty()).when(friendRepository).findByFriendIdAndUserDbo(friendId, userDbo);
        doReturn(friend).when(friendRepository).save(friend);

        final ResponseEntity<Response<SearchDto>> friendAddResponse = friendService.addFriend(friendId, userId, MockData.getAuthentication());

        assertNotNull(friendAddResponse.getBody().getData());
        assertEquals(friendAddResponse.getStatusCode(), HttpStatus.OK);
        verify(userRepository, times(1)).findByLoginDboUsername(MockData.getAuthentication().getName());
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findById(friendId);
        verify(userRepository, times(1)).findById(userId);
        verify(friendRepository, times(1)).findByFriendIdAndUserDbo(friendId, userDbo);
    }

    @Test
    public void getFriendList() {
        final UserDbo userDbo = MockData.userDbo();

        doReturn(Optional.of(userDbo)).when(userRepository).findById(userDbo.getId());
        userDbo.getFriendDbos().forEach(friend -> doReturn(Optional.of(userDbo)).when(userRepository).findById(friend.getFriendId()));

        final ResponseEntity<Response<List<SearchDto>>> friendResponseList = friendService.getFriendList(userDbo.getId());

        assertNotNull(friendResponseList.getBody().getData());
        assertEquals(friendResponseList.getStatusCode(), HttpStatus.OK);
        assertEquals(friendResponseList.getBody().getData().size(), userDbo.getFriendDbos().size());
        verify(userRepository, times(1)).findById(userDbo.getId());
        verify(userRepository, times(3)).findById(anyLong());
        verify(friendRepository, times(0)).deleteByFriendId(anyLong());
    }

    @Test
    public void deleteFromFriendList() {
        final UserDbo userDbo = MockData.userDbo();
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final List<UserDbo> findAllResult = Arrays.asList(userDbo, userDbo);
        final FriendDbo friend = MockData.friendDbo(userDbo, friendId);

        doReturn(Optional.of(userDbo)).when(userRepository).findById(userId);
        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(findAllResult).when(userRepository).findByLoginDboUsername(MockData.getAuthentication().getName());
        doReturn(Optional.of(friend)).when(friendRepository).findByFriendIdAndUserDbo(friendId, userDbo);
        doNothing().when(friendRepository).deleteById(friend.getId());

        final ResponseEntity<Response<String>> deleteResponse = friendService.deleteFromFriendList(friendId, userId, MockData.getAuthentication());

        assertNull(deleteResponse.getBody().getData());
        assertTrue(deleteResponse.getBody().getMessage().contains("was deleted"));
        assertEquals(deleteResponse.getStatusCode(), HttpStatus.OK);
        verify(userRepository, times(1)).findByLoginDboUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findById(userId);
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(friendRepository, times(1)).findByFriendIdAndUserDbo(friendId, userDbo);
        verify(friendRepository, times(1)).deleteById(friend.getId());
    }

    @TestConfiguration
    public static class FriendServiceTestConfiguration {
        @MockBean
        HateRepository hateRepository;
        @MockBean
        LikeRepository likeRepository;
        @MockBean
        PositionRepository positionRepository;
        @MockBean
        LanguageRepository languageRepository;

        @Bean
        public FriendService friendService(final FriendRepository friendRepository,
                                           final UserRepository userRepository,
                                           final LoginRepository loginRepository,
                                           final SearchService searchService,
                                           final UserConverter userConverter) {
            return new FriendService(friendRepository, userRepository, loginRepository, searchService, userConverter);
        }

        @Bean
        public SearchService searchService(final PositionRepository positionRepository,
                                           final UserRepository userRepository,
                                           final UserConverter userConverter,
                                           final HateRepository hateRepository,
                                           final LikeRepository likeRepository,
                                           final LanguageRepository languageRepository) {
            return new SearchService(positionRepository, userRepository, userConverter, hateRepository,
                    likeRepository, languageRepository);
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

        @Bean
        public UserConverter userConverter() {
            return new UserConverter();
        }
    }
}
