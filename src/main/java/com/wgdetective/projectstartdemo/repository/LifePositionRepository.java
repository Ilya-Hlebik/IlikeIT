package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.LIfePositionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifePositionRepository extends JpaRepository<LIfePositionDbo, Long> {

}
