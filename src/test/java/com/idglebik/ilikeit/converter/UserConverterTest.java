package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class UserConverterTest {

    @Autowired
    UserConverter userConverter;

    @Test
    public void convertToDto() {
        final UserDbo userDbo = MockData.userDbo();
        final UserDto userDto = userConverter.convertToDto(userDbo);
        assertEquals(userDbo.getFirstName(), userDto.getFirstName());
        assertEquals(userDbo.getLastName(), userDto.getLastName());
        assertEquals(userDbo.getAge(), userDto.getAge());
        assertEquals(userDbo.getSexes(), userDto.getSexes());
        assertEquals(userDbo.getCity(), userDto.getCity());
        assertEquals(userDbo.getCountry(), userDto.getCountry());
        assertEquals(userDbo.getAddress(), userDto.getAddress());
        assertEquals(userDbo.getGeneralInfo(), userDto.getGeneralInfo());
        assertEquals(userDbo.getOtherInfo(), userDto.getOtherInfo());
        assertEquals(userDbo.getPositions().iterator().next().getPositionName(),
                userDto.getPositions().iterator().next().getPositionName());
        assertEquals(userDbo.getStudies().iterator().next().getInstitution(),
                userDto.getStudies().iterator().next().getInstitution());
        assertEquals(userDbo.getLanguages().iterator().next().getLanguage(),
                userDto.getLanguages().iterator().next().getLanguage());
        assertEquals(userDbo.getLikes().iterator().next().getLike(), userDto.getLikes().iterator().next().getLike());
        assertEquals(userDbo.getHates().iterator().next().getHate(), userDto.getHates().iterator().next().getHate());
        assertEquals(userDbo.getLifePosition().getAligment(), userDto.getLifePosition().getAligment());
        assertEquals(userDbo.getLifePosition().getMainInLife(), userDto.getLifePosition().getMainInLife());
        assertEquals(userDbo.getLifePosition().getMainInPeople(), userDto.getLifePosition().getMainInPeople());
        assertForFriends(userDto, userDbo);
    }

    @Test
    public void convertToDbo() {
        final UserDto userDto = MockData.userDto();
        final UserDbo userDbo = userConverter.convertToDbo(userDto);
        assertEquals(userDto.getFirstName(), userDbo.getFirstName());
        assertEquals(userDto.getLastName(), userDbo.getLastName());
        assertEquals(userDto.getAge(), userDbo.getAge());
        assertEquals(userDto.getSexes(), userDbo.getSexes());
        assertEquals(userDto.getCity(), userDbo.getCity());
        assertEquals(userDto.getCountry(), userDbo.getCountry());
        assertEquals(userDbo.getAddress(), userDto.getAddress());
        assertEquals(userDto.getGeneralInfo(), userDbo.getGeneralInfo());
        assertEquals(userDto.getOtherInfo(), userDbo.getOtherInfo());
        assertEquals(userDto.getPositions().iterator().next().getPositionName(),
                userDbo.getPositions().iterator().next().getPositionName());
        assertEquals(userDto.getStudies().iterator().next().getInstitution(),
                userDbo.getStudies().iterator().next().getInstitution());
        assertEquals(userDto.getLanguages().iterator().next().getLanguage(),
                userDbo.getLanguages().iterator().next().getLanguage());
        assertEquals(userDto.getLikes().iterator().next().getLike(), userDbo.getLikes().iterator().next().getLike());
        assertEquals(userDto.getHates().iterator().next().getHate(), userDbo.getHates().iterator().next().getHate());
        assertEquals(userDto.getLifePosition().getAligment(), userDbo.getLifePosition().getAligment());
        assertEquals(userDto.getLifePosition().getMainInLife(), userDbo.getLifePosition().getMainInLife());
        assertEquals(userDto.getLifePosition().getMainInPeople(), userDbo.getLifePosition().getMainInPeople());
        assertForFriends(userDto, userDbo);
    }

    private void assertForFriends(UserDto userDto, UserDbo userDbo) {
        final boolean[] isContains = {false};
        userDbo.getFriendDbos().forEach(e ->
                userDto.getFriendDtos().forEach(b ->
                {
                    if (b.getFriendId() == e.getFriendId()) {
                        isContains[0] = true;
                    }
                }));
        assertTrue(isContains[0]);
        isContains[0] = false;
    }

    @TestConfiguration
    public static class UserConverterTestConfiguration {
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
