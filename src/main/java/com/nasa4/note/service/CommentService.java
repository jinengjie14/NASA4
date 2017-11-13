package com.nasa4.note.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nasa4.note.domain.Comment;
import com.nasa4.note.web.validform.CommentForm;

public interface CommentService {
	
	public void save(String sessionUserId, CommentForm commentForm, String reload);
	
	public Page<Comment> findAll(Pageable pageable);

}
