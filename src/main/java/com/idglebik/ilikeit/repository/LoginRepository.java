package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LoginDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginDbo, Long> {
    LoginDbo findByUsername(String username);
}