package com.nasa4.note.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nasa4.note.domain.Note;
import com.nasa4.note.web.validform.NoteForm;

public interface NoteService {

	public String save(String sessionUserId, NoteForm noteForm, String reload);
	
	public Note findOne(String id);
	public Note findOne(String sessionUserId, String id, String reload);
	
	public void delete(String sessionUserId, String id, String reload);
	
	public void update(String sessionUserId, String id, NoteForm noteForm, String reload);
	
	public Page<Note> findAll(Pageable pageable, String keyword);
	
	public Page<Note> findByUserId(Pageable pageable, String sessionUserId, String reload);
}
