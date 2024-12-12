package com.jamesobin.hourgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamesobin.hourgram.post.dto.CardDTO;
import com.jamesobin.hourgram.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/input-view")
	public String createContents() {
		return "post/input";
	}
	
	@GetMapping("/timeline-view")
	public String timeline(Model model) {
		
		List<CardDTO> cardList = postService.getPostList();
		
		model.addAttribute("cardList", cardList);
		
		return "post/timeline";
	}
	
}
