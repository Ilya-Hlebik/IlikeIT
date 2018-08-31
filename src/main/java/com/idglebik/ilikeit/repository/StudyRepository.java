package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.StudyDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyDbo, Long> {

}
