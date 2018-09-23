package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRepository extends JpaRepository<LoginDbo, Long> {
    LoginDbo findByUsername(String username);

    @Query(value = "SELECT login.ID from login join user on LOGIN_ID = login.ID where user.ID = ?1", nativeQuery = true)
    LoginDbo findByUserId(long userId);
}