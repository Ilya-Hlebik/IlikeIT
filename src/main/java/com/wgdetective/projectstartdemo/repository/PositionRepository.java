package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.enumerated.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionDbo, Long> {
    Optional<PositionDbo> findByPositionName(Position positionName);
}
