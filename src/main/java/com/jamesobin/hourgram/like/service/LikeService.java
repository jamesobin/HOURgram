package com.jamesobin.hourgram.like.service;

import org.springframework.stereotype.Service;

import com.jamesobin.hourgram.like.domain.Like;
import com.jamesobin.hourgram.like.repository.LikeRepository;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	public boolean addLike(int postId, int userId) {
		
		Like like = Like.builder()
				.postId(postId)
				.userId(userId)
				.build();
		
		try {			
			likeRepository.save(like);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
}
