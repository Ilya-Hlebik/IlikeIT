package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import com.wgdetective.projectstartdemo.enumerated.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PositionRepository extends JpaRepository<PositionDbo, Long> {
    Optional<PositionDbo> findByPositionName   (String positionName);
}
