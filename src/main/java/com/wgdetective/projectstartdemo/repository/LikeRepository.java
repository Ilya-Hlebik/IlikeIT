package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.LikeDbo;
import com.wgdetective.projectstartdemo.enumerated.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeDbo, Long> {
    LikeDbo findByLike(Like like);
}
