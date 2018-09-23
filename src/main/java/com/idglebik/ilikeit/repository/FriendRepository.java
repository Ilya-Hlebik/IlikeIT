package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface FriendRepository extends JpaRepository<FriendDbo, Long> {

    Optional<FriendDbo> findByFriendIdAndUserDbo(long friendId, UserDbo userDbo);
    void deleteByFriendId(long friendId);
    void deleteByUserDboId(long userId);
}
