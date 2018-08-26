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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    /*    for (PositionDto position: userDto.getPosition()){
           List<PositionDbo>  positionDbo =   (positionRepository.findByPosition(position.getPosition()));
           for (PositionDbo positionDbo1: positionDbo) {
               System.out.println(positionDbo1.getPosition());
               userDto.getPosition().add(positionConverter.convertToDto(positionDbo1));

           }
        }*/
        userRepository.save(userConverter.convertToDbo(userDto));
    }

    public List<UserDto> getUserList(){

         return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }
}
