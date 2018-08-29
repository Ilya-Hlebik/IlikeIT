package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.HateDbo;
import com.wgdetective.projectstartdemo.enumerated.Hate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HateRepository extends JpaRepository<HateDbo, Long> {
    HateDbo findByHate(Hate hate);
}
