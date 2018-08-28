package com.wgdetective.projectstartdemo.service;

import com.wgdetective.projectstartdemo.converter.PositionConverter;
import com.wgdetective.projectstartdemo.converter.StudyConverter;
import com.wgdetective.projectstartdemo.converter.UserConverter;
import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.dbo.StudyDbo;
import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import com.wgdetective.projectstartdemo.dto.StudyDto;
import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.enumerated.Position;
import com.wgdetective.projectstartdemo.repository.PositionRepository;
import com.wgdetective.projectstartdemo.repository.StudyRepository;
import com.wgdetective.projectstartdemo.repository.UserRepository;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PositionRepository positionRepository;
    private final StudyConverter studyConverter;
    private final StudyRepository studyRepository;

    @Autowired
    public UserService(final UserRepository userRepository, final UserConverter userConverter, final PositionRepository positionRepository, final StudyConverter studyConverter, final StudyRepository studyRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.positionRepository = positionRepository;
        this.studyConverter = studyConverter;
        this.studyRepository = studyRepository;
    }

    @Transactional
    public void createUser(final UserDto userDto) {
        final UserDbo userDbo = userConverter.convertToDbo(userDto);

        Set<StudyDto> studyDbos = userDto.getStudys();
        Set<StudyDbo> studyDbos1 = new HashSet<>();
        BeanUtils.copyProperties(studyDbos, studyDbos1);

        studyDbos1.forEach( study -> study.setUserDbo(userDbo) );

        userDbo.setPosition(userDto.getPosition().stream().map(this::getPositionDBO).collect(Collectors.toSet()));
        userRepository.save(userDbo);
    }

    public List<UserDto> getUserList() {

        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public PositionDbo getPositionDBO(PositionDto positionDto) {
        return positionRepository.findByPositionName(positionDto.getPositionName());
    }
    @Transactional
    public StudyDbo getStudysDbo(StudyDto studyDto) {
        return studyRepository.save( studyConverter.convertToDbo(studyDto));
    }
}
