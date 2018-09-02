package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.converter.LifePositionConverter;
import com.idglebik.ilikeit.converter.StudyConverter;
import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.*;
import com.idglebik.ilikeit.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PositionRepository positionRepository;
    private final StudyConverter studyConverter;
    private final LanguageRepository languageRepository;
    private final LikeRepository likeRepository;
    private final HateRepository hateRepository;
    private final LifePositionConverter lifePositionConverter;
    private final LifePositionRepository lifePositionRepository;

    @Transactional
    public UserDto createUser(final UserDto userDto) {
        final UserDbo userDbo = userConverter.convertToDbo(userDto);
        Set<StudyDbo> studyDbos = userDto.getStudys().stream().map(studyConverter::convertToDbo).collect(Collectors.toSet());
        studyDbos.forEach(studyDbo -> studyDbo.setUserDbo(userDbo));
        userDbo.setStudys(studyDbos);
        LIfePositionDbo lIfePositionDbo = lifePositionConverter.convertToDbo(userDto.getLifePosition());
        lIfePositionDbo.setUser(userDbo);

        userDbo.setLIfePosition(lIfePositionDbo);

        userDbo.setPosition(userDto.getPosition().stream().map(this::getPositionDBO).collect(Collectors.toSet()));
        userDbo.setLike(userDto.getLike().stream().map(this::getLikeDBO).collect(Collectors.toSet()));
        userDbo.setHate(userDto.getHate().stream().map(this::getHateDBO).collect(Collectors.toSet()));
        userDbo.setLanguage(userDto.getLanguage().stream().map(this::getLanguageDBO).collect(Collectors.toSet()));
        return userConverter.convertToDto(userRepository.save(userDbo));
    }

    private HateDbo getHateDBO(HateDto hateDto) {
        return hateRepository.findByHate(hateDto.getHate());
    }

    private LikeDbo getLikeDBO(LikeDto likeDto) {

        return likeRepository.findByLike(likeDto.getLike());
    }

    @Transactional
    public LangDbo getLanguageDBO(LangDto langDto) {
        return languageRepository.findByLanguage(langDto.getLanguage());
    }

    public List<UserDto> getUserList() {
        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }


    private PositionDbo getPositionDBO(PositionDto positionDto) {
        return positionRepository.findByPositionName(positionDto.getPositionName());
    }

    public String deleteUser(Long userID) {
        userRepository.deleteById(userID);
        return "User was deleted";
    }

    @Transactional
    public UserDto updateUser(Long userID, UserDto userDto) {
        UserDbo user = userRepository.getOne(userID);
        user.setAge(userDto.getAge());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setGeneralInfo(userDto.getGeneralInfo());
        user.setOtherInfo(userDto.getOtherInfo());
        user.setLike(userDto.getLike().stream().map(this::getLikeDBO).collect(Collectors.toSet()));
        user.setHate(userDto.getHate().stream().map(this::getHateDBO).collect(Collectors.toSet()));
        user.setLanguage(userDto.getLanguage().stream().map(this::getLanguageDBO).collect(Collectors.toSet()));
        user.setPosition(userDto.getPosition().stream().map(this::getPositionDBO).collect(Collectors.toSet()));

        lifePositionRepository.removeByUser(user);
        LIfePositionDbo lIfePositionDbo = lifePositionConverter.convertToDbo(userDto.getLifePosition());
        lIfePositionDbo.setUser(user);
        user.setLIfePosition(lIfePositionDbo);

        Set<StudyDbo> studyDbos = userDto.getStudys().stream().map(studyConverter::convertToDbo).collect(Collectors.toSet());
        studyDbos.forEach(studyDbo -> studyDbo.setUserDbo(user));
        user.setStudys(studyDbos);

        return userConverter.convertToDto(userRepository.save(user));
    }

    public UserDto getUser(Long userId) {
        return userConverter.convertToDto(userRepository.getOne(userId));
    }
}
