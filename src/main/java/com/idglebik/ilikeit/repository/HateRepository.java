package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.HateDbo;
import com.idglebik.ilikeit.enumerated.Hate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HateRepository extends JpaRepository<HateDbo, Long> {
    HateDbo findByHate(Hate hate);
}
