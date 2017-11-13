package com.nasa4.note.web.validform;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentForm {
	
	@NotBlank(message="comment.content.isnull")
	private String content;
	private String noteId;

}
