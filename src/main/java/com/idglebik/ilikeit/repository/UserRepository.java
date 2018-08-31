package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
      /*  List<UserDbo> findAllByUserDbo(UserDto userDto);*/
}
