package com.marondal.marondalgram.like.repository;

import com.marondal.marondalgram.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // SELECT COUNT(*) FROM `like` WHERE `postId` = #{}
    public int countByPostId(long postId);

    // WHERE `post_id` = #{} AND `user_id` = #{}
    public boolean existsByPostIdAndUserId(long postId, long userId);

}
