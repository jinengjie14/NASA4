package com.nasa4.note.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasa4.note.dao.NoteRepository;
import com.nasa4.note.domain.Note;
import com.nasa4.note.service.BaseService;
import com.nasa4.note.utils.MarkdownUtil;
import com.nasa4.note.web.validform.NoteForm;

@Service
public class NoteServiceImpl extends BaseService{
	@Autowired
	private NoteRepository noteRepository;
	
	public void save(String userid, NoteForm noteForm, String reload) {
		checkLogin(userid, reload);
		Note note = new Note(userid);
		BeanUtils.copyProperties(noteForm, note, Note.class);
		note.setDescription(MarkdownUtil.substring(noteForm.getContent(), 30, " ..."));
		note.setImage(MarkdownUtil.getFirstImage(noteForm.getContent()));
		noteRepository.save(note);
	}
	
	public Note findOne(String id) {
		return noteRepository.findOne(id);
	}

}
