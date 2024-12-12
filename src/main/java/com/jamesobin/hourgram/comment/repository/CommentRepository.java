package com.jamesobin.hourgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hourgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	// SELECT `contents` FROM `post` WHERE `postId`= #{}
	public List<Comment> findByPostId(int postId);
	
}
