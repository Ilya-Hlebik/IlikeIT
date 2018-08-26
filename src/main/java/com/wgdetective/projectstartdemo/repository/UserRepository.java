package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
      /*  List<UserDbo> findAllByUserDbo(UserDto userDto);*/
}
