package com.jamesobin.hourgram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDTO {

	private int commentId;
	private int userId;
	
	private String loginId;
	private String contents;
	private String profileName;
	
}
