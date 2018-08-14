package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionDbo, Long> {
}
