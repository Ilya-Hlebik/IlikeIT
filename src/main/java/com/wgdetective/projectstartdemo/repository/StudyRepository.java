package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.StudyDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyDbo, Long> {

}
