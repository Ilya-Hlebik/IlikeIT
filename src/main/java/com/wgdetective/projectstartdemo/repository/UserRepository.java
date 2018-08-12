package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
}
