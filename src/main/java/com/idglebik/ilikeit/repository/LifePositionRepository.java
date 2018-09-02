package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LIfePositionDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifePositionRepository extends JpaRepository<LIfePositionDbo, Long> {
    void removeByUser(UserDbo userDbo);
}
