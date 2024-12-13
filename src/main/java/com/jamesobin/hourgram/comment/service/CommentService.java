package com.jamesobin.hourgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jamesobin.hourgram.comment.domain.Comment;
import com.jamesobin.hourgram.comment.dto.CommentDTO;
import com.jamesobin.hourgram.comment.repository.CommentRepository;
import com.jamesobin.hourgram.user.domain.User;
import com.jamesobin.hourgram.user.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
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
	
	public List<CommentDTO> getCommentList(int postId) {
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentDTO> commentDTOList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			int userId = comment.getUserId();
			
			User user = userService.getUserById(userId);
			
			CommentDTO commentDTO = CommentDTO.builder()
					.commentId(comment.getId())
					.userId(userId)
					.loginId(user.getLoginId())
					.profileName(user.getProfileName())
					.contents(comment.getContents())
					.build();
			
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	}

}