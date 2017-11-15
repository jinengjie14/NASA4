package com.nasa4.note.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nasa4.note.dao.NoteRepository;
import com.nasa4.note.domain.Note;
import com.nasa4.note.exception.ServiceException;
import com.nasa4.note.service.BaseService;
import com.nasa4.note.service.NoteService;
import com.nasa4.note.utils.MarkdownUtil;
import com.nasa4.note.web.validform.NoteForm;

@Service
public class NoteServiceImpl extends BaseService implements NoteService{
	@Autowired
	private NoteRepository noteRepository;
	
	public String save(String sessionUserId, NoteForm noteForm, String reload) {
		checkLogin(sessionUserId, reload);
		Note note = new Note(sessionUserId);
		BeanUtils.copyProperties(noteForm, note, Note.class);
		note.setDescription(MarkdownUtil.substring(noteForm.getContent(), 255, " ..."));
		note.setImage(MarkdownUtil.getFirstImage(noteForm.getContent()));
		noteRepository.save(note);
		return note.getId();
	}
	
	public void delete(String sessionUserId, String id, String reload) {
		Note note = noteRepository.findOne(id);
		if(null == note) {
			throw new ServiceException("该文章不存在");
		}
		checkLogin(sessionUserId, reload);
		checkOwn(sessionUserId, note.getUser().getId());
		noteRepository.delete(id);
	}
	
	public void update(String sessionUserId, String id, NoteForm noteForm, String reload) {
		Note note = noteRepository.findOne(id);
		if(null == note) {
			throw new ServiceException("该文章不存在");
		}
		checkLogin(sessionUserId, reload);
		checkOwn(sessionUserId, note.getUser().getId());
		BeanUtils.copyProperties(noteForm, note, Note.class);
		note.setDescription(MarkdownUtil.substring(noteForm.getContent(), 255, " ..."));
		note.setImage(MarkdownUtil.getFirstImage(noteForm.getContent()));
		noteRepository.saveAndFlush(note);
	}
	
	public Note findOne(String id) {
		return noteRepository.findOne(id);
	}
	
	public Note findOne(String sessionUserId, String id, String reload) {
		Note note = noteRepository.findOne(id);
		if(null == note) {
			throw new ServiceException("该文章不存在");
		}
		checkLogin(sessionUserId, reload);
		checkOwn(sessionUserId, note.getUser().getId());
		return note;
	}
	
	public Page<Note> findAll(Pageable pageable, String keyword) {
		return noteRepository.findByTitleLikeOrContentLike(pageable, keyword);
	}
	
	public Page<Note> findByUserId(Pageable pageable, String sessionUserId, String reload) {
		return noteRepository.findByUserId(pageable, sessionUserId);
	}

}
