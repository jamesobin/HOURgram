package com.jamesobin.hourgram.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hourgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	// SELECT count(*) FROM `like` WHERE `postId` = #{}
	public int countByPostId(int postId);
	
	// SELECT count(*) FROM `like` WHERE `postId` = #{} AND `userId` = #{};
	public int countByPostIdAndUserId(int postId, int userId);
	
}
