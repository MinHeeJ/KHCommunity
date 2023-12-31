package com.kh.app.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentDto {

	private int postId;
	private int boardId;
	@NotBlank (message="댓글내용을 작성해주세요.")
	private String commentContent;
	private boolean anonymousCheck;
	private String commentRef;
	
}
