package com.jamesobin.hourgram.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDTO {
	
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String profileName;

}
