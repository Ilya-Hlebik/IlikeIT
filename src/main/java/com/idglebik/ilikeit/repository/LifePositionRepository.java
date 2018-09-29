package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LifePositionDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifePositionRepository extends JpaRepository<LifePositionDbo, Long> {
    void removeByUser(UserDbo userDbo);
}
