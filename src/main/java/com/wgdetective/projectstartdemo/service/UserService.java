package com.wgdetective.projectstartdemo.service;

import com.wgdetective.projectstartdemo.converter.PositionConverter;
import com.wgdetective.projectstartdemo.converter.UserConverter;
import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.enumerated.Position;
import com.wgdetective.projectstartdemo.repository.PositionRepository;
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

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PositionRepository positionRepository;
    private final PositionConverter positionConverter;

    @Autowired
    public UserService(final UserRepository userRepository, final UserConverter userConverter, final PositionRepository positionRepository, final PositionConverter positionConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.positionRepository = positionRepository;
        this.positionConverter = positionConverter;
    }

    @Transactional
    public void createUser(final UserDto userDto){
        final UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(userDto, userDbo);
        userDbo.setPosition(userDto.getPosition().stream().map(this::getPositionDBO).collect(Collectors.toSet()));
        userRepository.save(userDbo);
    }

    public List<UserDto> getUserList(){

         return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public PositionDbo getPositionDBO(PositionDto positionDto) {
        return positionRepository.findByPositionName(positionDto.getPositionName());
    }
}
