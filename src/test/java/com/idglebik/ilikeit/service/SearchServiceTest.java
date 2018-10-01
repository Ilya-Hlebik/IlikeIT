package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.*;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.LifePositionDto;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Hate;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class SearchServiceTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    SearchService searchService;
    @MockBean
    PositionRepository positionRepository;
    @MockBean
    HateRepository hateRepository;
    @MockBean
    LikeRepository likeRepository;
    @MockBean
    LanguageRepository languageRepository;

    @Test
    public void searchUsersByPosition() {
        final PositionDbo positionDbo = MockData.positionDbo();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(positionDbo).when(positionRepository).findByPositionName(positionDbo.getPositionName());
        doReturn(userDbos).when(userRepository).findByPositions(positionDbo);

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByPosition(positionDbo.getPositionName());

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(userRepository, times(1)).findByPositions(positionDbo);
        verify(positionRepository, times(1)).findByPositionName(positionDbo.getPositionName());
    }

    @Test
    public void searchUsersByHate() {
        final HateDbo hateDbo = MockData.hateDbo();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(hateDbo).when(hateRepository).findByHate(hateDbo.getHate());
        doReturn(userDbos).when(userRepository).findByHates(hateDbo);

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByHate(Hate.BOOKS);

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(hateRepository, times(1)).findByHate(hateDbo.getHate());
        verify(userRepository, times(1)).findByHates(hateDbo);
    }

    @Test
    public void searchUsersByLike() {
        final LikeDbo likeDbo = MockData.likeDbo();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(likeDbo).when(likeRepository).findByLike(likeDbo.getLike());
        doReturn(userDbos).when(userRepository).findByLikes(likeDbo);

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByLike(likeDbo.getLike());

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(likeRepository, times(1)).findByLike(likeDbo.getLike());
        verify(userRepository, times(1)).findByLikes(likeDbo);
    }

    @Test
    public void searchUsersByLang() {
        final LangDbo langDbo = MockData.langDbo();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(langDbo).when(languageRepository).findByLanguage(langDbo.getLanguage());
        doReturn(userDbos).when(userRepository).findByLanguages(langDbo);

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByLang(langDbo.getLanguage());

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(languageRepository, times(1)).findByLanguage(langDbo.getLanguage());
        verify(userRepository, times(1)).findByLanguages(langDbo);
    }

    @Test
    public void searchUsersByLifePosition() {
        final LifePositionDto lifePositionDto = MockData.lifePositionDto();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(userDbos).when(userRepository).findAllByLifePosition_AligmentAndLifePosition_MainInLifeAndLifePosition_MainInPeople
                (lifePositionDto.getAligment(), lifePositionDto.getMainInLife(), lifePositionDto.getMainInPeople());

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByLifePosition(lifePositionDto);

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(userRepository, times(1)).findAllByLifePosition_AligmentAndLifePosition_MainInLifeAndLifePosition_MainInPeople
                (lifePositionDto.getAligment(), lifePositionDto.getMainInLife(), lifePositionDto.getMainInPeople());
    }

    @Test
    public void searchUsersByLikeAndHate() {
        final LikeDbo likeDbo = MockData.likeDbo();
        final HateDbo hateDbo = MockData.hateDbo();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(hateDbo).when(hateRepository).findByHate(hateDbo.getHate());
        doReturn(likeDbo).when(likeRepository).findByLike(likeDbo.getLike());
        doReturn(userDbos).when(userRepository).findByLikesAndHates(likeDbo, hateDbo);

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByLikeAndHate(likeDbo.getLike(),
                hateDbo.getHate());

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(hateRepository, times(1)).findByHate(hateDbo.getHate());
        verify(likeRepository, times(1)).findByLike(likeDbo.getLike());
    }

    @Test
    public void searchUsersByLastNameAndCityAndCountry() {
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());
        final UserDto userDto = MockData.userDto();

        doReturn(userDbos).when(userRepository).findAllByLastNameAndCityAndCountry(userDto.getLastName(), userDto.getCity(),
                userDto.getCountry());

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.searchUsersByLastNameAndCityAndCountry(
                userDto.getLastName(), userDto.getCity(), userDto.getCountry());

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(userRepository, times(1)).findAllByLastNameAndCityAndCountry(userDto.getLastName(),
                userDto.getCity(), userDto.getCountry());

    }

    @Test
    public void findByFirstNameAndLastName() {
        final UserDto userDto = MockData.userDto();
        final List<UserDbo> userDbos = Arrays.asList(MockData.userDbo(), MockData.userDbo());

        doReturn(userDbos).when(userRepository).findAllByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());

        ResponseEntity<Response<List<SearchDto>>> searchResponse = searchService.findByFirstNameAndLastName(userDto);

        assertNotNull(searchResponse.getBody().getData());
        assertEquals(searchResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(searchResponse.getBody().getData().size(), userDbos.size());
        verify(userRepository, times(1)).findAllByFirstNameAndLastName(userDto.getFirstName(),
                userDto.getLastName());
    }

    @TestConfiguration
    public static class FriendServiceTestConfiguration {

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
}
