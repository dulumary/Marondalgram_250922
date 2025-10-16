package com.marondal.marondalgram.like.repository;

import com.marondal.marondalgram.like.domain.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // SELECT COUNT(*) FROM `like` WHERE `postId` = #{}
    public int countByPostId(long postId);

    // WHERE `post_id` = #{} AND `user_id` = #{}
    public boolean existsByPostIdAndUserId(long postId, long userId);

    // WHERE `post_id` = #{} AND `user_id` = #{}
    public Optional<Like> findByPostIdAndUserId(long postId, long userId);

    // SELECT * FROM `like` WHERE `postId` = #{}
    // DELETE FROM `like` WHERE `postId` = #{}
    // transaction
    // 필요에 따라 여러쿼리가 한번에 수행되야 하는 경우
    // 쿼리들을 transaction 이라는 단위로 묶어서 한번에 수행
    // 이 과정에서 다른 쿼리는 해당 쿼리들이 모두 수행 될때까지 대기
    // Rollback : 이전 상태로 되 돌린다.
    // transaction 기반의 쿼리 수행 과정 중 에러가 발생되면 쿼리 수행 이전으로 되돌리는 Rollback 이 수행

    // 삭제 과정에서 문제가 생기는 경우 진행된 결과를 되돌린다.
    @Transactional
    public void deleteByPostId(long postId);
}
