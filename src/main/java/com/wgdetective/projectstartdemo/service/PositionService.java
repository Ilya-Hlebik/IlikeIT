package com.wgdetective.projectstartdemo.service;

import com.wgdetective.projectstartdemo.converter.PositionConverter;
import com.wgdetective.projectstartdemo.converter.UserConverter;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import com.wgdetective.projectstartdemo.dto.UserDto;
import com.wgdetective.projectstartdemo.repository.PositionRepository;
import com.wgdetective.projectstartdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {
    private final PositionRepository positionRepository;
    private final PositionConverter positionConverter;

    @Autowired
    public PositionService(final PositionRepository positionRepository, final PositionConverter positionConverter){
        this.positionRepository = positionRepository;
        this.positionConverter = positionConverter;
    }

    public void createPositionForUser(final PositionDto positionDto){
        positionRepository.save(positionConverter.convertToDbo(positionDto));
    }

}
