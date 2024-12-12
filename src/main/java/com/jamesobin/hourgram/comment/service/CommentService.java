package com.jamesobin.hourgram.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jamesobin.hourgram.comment.domain.Comment;
import com.jamesobin.hourgram.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
		Comment comment = Comment.builder()
				.postId(postId)
				.userId(userId)
				.contents(contents)
				.build();
		
		try {			
			commentRepository.save(comment);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Comment> getCommentList(int postId) {
		return commentRepository.findByPostId(postId);
	}

}