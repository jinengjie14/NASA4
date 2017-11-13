package com.nasa4.note.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nasa4.note.dao.CommentRepository;
import com.nasa4.note.domain.Comment;
import com.nasa4.note.domain.Note;
import com.nasa4.note.service.BaseService;
import com.nasa4.note.service.CommentService;
import com.nasa4.note.web.validform.CommentForm;

@Service
public class CommentServiceImpl extends BaseService implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public void save(String sessionUserId, CommentForm commentForm, String reload) {
		checkLogin(sessionUserId, reload);
		Comment comment = new Comment(sessionUserId);
		BeanUtils.copyProperties(commentForm, comment, Comment.class);
		Note note = new Note();
		note.setId(commentForm.getNoteId());
		comment.setNote(note);
		commentRepository.save(comment);
	}
	
	public Page<Comment> findAll(Pageable pageable) {
		return commentRepository.findAll(pageable);
	}
}
