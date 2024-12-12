package com.jamesobin.hourgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jamesobin.hourgram.comment.domain.Comment;
import com.jamesobin.hourgram.comment.service.CommentService;
import com.jamesobin.hourgram.common.FileManager;
import com.jamesobin.hourgram.like.service.LikeService;
import com.jamesobin.hourgram.post.domain.Post;
import com.jamesobin.hourgram.post.dto.CardDTO;
import com.jamesobin.hourgram.post.repository.PostRepository;
import com.jamesobin.hourgram.user.domain.User;
import com.jamesobin.hourgram.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	public PostService(PostRepository postRepository
			, UserService userService
			, LikeService likeService
			, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.commentService = commentService;
	}

	public boolean addPost(int userId, String contents, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {			
			postRepository.save(post);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public List<CardDTO> getPostList(int loginUserId) {
		
		// 조회된 게시글마다 작성자의 로그인 ID 얻어오기
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			int likeCount = likeService.getLikeCount(post.getId());
			
			boolean isLike = likeService.isLike(post.getId(), loginUserId);
			
			List<Comment> commentList = commentService.getCommentList(post.getId());
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.profileName(user.getProfileName())
			.likeCount(likeCount)
			.isLike(isLike) // .멤버변수(값)
			.commentList(commentList)
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
	}

}
