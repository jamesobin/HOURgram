package com.jamesobin.hourgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jamesobin.hourgram.common.FileManager;
import com.jamesobin.hourgram.post.domain.Post;
import com.jamesobin.hourgram.post.dto.CardDTO;
import com.jamesobin.hourgram.post.repository.PostRepository;
import com.jamesobin.hourgram.user.domain.User;
import com.jamesobin.hourgram.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
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
	
	public List<CardDTO> getPostList() {
		
		// 조회된 게시글마다 작성자의 로그인 ID 얻어오기
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.profileName(user.getProfileName())
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
	}

}
