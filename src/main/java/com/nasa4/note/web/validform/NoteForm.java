package com.nasa4.note.web.validform;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class NoteForm {
	@NotBlank(message = "note.title.isnull")
	private String title;
	@NotBlank(message = "note.markdown.isnull")
	private String content;

}
