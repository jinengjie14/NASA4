package com.nasa4.note.web.validform;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class NoteForm {
	@NotBlank
	private String title;
	private String content;

}
