package com.wgdetective.projectstartdemo.service;

import com.wgdetective.projectstartdemo.converter.LifePositionConverter;
import com.wgdetective.projectstartdemo.converter.StudyConverter;
import com.wgdetective.projectstartdemo.converter.UserConverter;
import com.wgdetective.projectstartdemo.dbo.*;
import com.wgdetective.projectstartdemo.dto.*;
import com.wgdetective.projectstartdemo.repository.*;
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

    @Transactional
    public void createUser(final UserDto userDto) {
        final UserDbo userDbo = userConverter.convertToDbo(userDto);
        Set<StudyDbo> studyDbos = userDto.getStudys().stream().map(studyConverter::convertToDbo).collect(Collectors.toSet());
        studyDbos.forEach(studyDbo -> studyDbo.setUserDbo(userDbo));
        userDbo.setStudys(studyDbos);
        LIfePositionDbo lIfePositionDbo = lifePositionConverter.convertToDbo(userDto.getLifePositionDto());
        lIfePositionDbo.setUser(userDbo);
        userDbo.setLIfePositionDbo(lIfePositionDbo);

        userDbo.setPosition(userDto.getPosition().stream().map(this::getPositionDBO).collect(Collectors.toSet()));
        userDbo.setLike(userDto.getLike().stream().map(this::getLikeDBO).collect(Collectors.toSet()));
        userDbo.setHate(userDto.getHate().stream().map(this::getHateDBO).collect(Collectors.toSet()));
        userDbo.setLanguage(userDto.getLanguage().stream().map(this::getLanguageDBO).collect(Collectors.toSet()));
        userRepository.save(userDbo);
    }

    private HateDbo getHateDBO(HateDto hateDto) {
        return hateRepository.findByHate(hateDto.getHate());
    }

    private LikeDbo getLikeDBO(LikeDto likeDto) {

        return likeRepository.findByLike(likeDto.getLike());
    }

    @Transactional
    private LangDbo getLanguageDBO(LangDto langDto) {
        return languageRepository.findByLanguage(langDto.getLanguage());
    }

    public List<UserDto> getUserList() {

        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }


    public PositionDbo getPositionDBO(PositionDto positionDto) {
        return positionRepository.findByPositionName(positionDto.getPositionName());
    }
}
