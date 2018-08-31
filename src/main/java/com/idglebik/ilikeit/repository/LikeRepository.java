package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LikeDbo;
import com.idglebik.ilikeit.enumerated.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeDbo, Long> {
    LikeDbo findByLike(Like like);
}
