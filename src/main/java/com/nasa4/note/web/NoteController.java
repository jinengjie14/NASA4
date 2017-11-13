package com.nasa4.note.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nasa4.note.domain.Note;
import com.nasa4.note.service.NoteService;
import com.nasa4.note.utils.Result;
import com.nasa4.note.web.validform.NoteForm;

@Controller
public class NoteController extends BaseController {
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/input")
	public String input(Model model) {
		return "note_input";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable String id) {
		model.addAttribute("note", noteService.findOne(sessionUserId, id, reload));
		return "note_edit";
	}
	
	@GetMapping("/note/{id}")
	public String note(Model model, @PathVariable String id) {
		Note note = noteService.findOne(id);
		model.addAttribute("note", note);
		return "show";
	}
	
	@PostMapping("/note")
	@ResponseBody
	public Result save(@Valid NoteForm noteForm) {
		String id = noteService.save(sessionUserId, noteForm, reload);
		return Result.success("/note/"+id, "发布成功");
	}
	
	@PutMapping("/note/{id}")
	@ResponseBody
	public Result update(@Valid NoteForm noteForm, @PathVariable String id) {
		noteService.update(sessionUserId, id, noteForm, reload);
		return Result.success("/note/"+id, "修改成功");
	}
	
	@DeleteMapping("/note/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		noteService.delete(sessionUserId, id, reload);
		return Result.success(reload, "删除成功");
	}
}
