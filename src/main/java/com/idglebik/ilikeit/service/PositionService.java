package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.converter.PositionConverter;
import com.idglebik.ilikeit.dto.PositionDto;
import com.idglebik.ilikeit.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
