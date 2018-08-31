package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.enumerated.Position;
import com.idglebik.ilikeit.dbo.PositionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionDbo, Long> {
    PositionDbo findByPositionName(Position positionName);
}
