package com.nasa4.note.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nasa4.note.domain.Note;
import com.nasa4.note.service.impl.NoteServiceImpl;
import com.nasa4.note.utils.Result;
import com.nasa4.note.web.validform.NoteForm;

@Controller
public class NoteController extends BaseController {
	@Autowired
	private NoteServiceImpl noteServiceImpl;
	
	@GetMapping("/input")
	public String input(Model model) {
		getUrl(model);
		return "note_input";
	}
	
	@GetMapping("show")
	public String show() {
		return "show";
	}
	
	@GetMapping("/note/{id}")
	@ResponseBody
	public String note(Model model, @PathVariable String id) {
		Note note = noteServiceImpl.findOne(id);
		model.addAttribute("note", note);
		return "";
	}
	
	@PostMapping("/note")
	@ResponseBody
	public Result save(@Valid NoteForm noteForm) {
		noteServiceImpl.save(sessionUserId, noteForm, reload);
		return new Result("/", 200, "发布成功");
	}
	
	@PutMapping("/note/{id}")
	@ResponseBody
	public Result update(@Valid NoteForm noteForm) {
		
		return Result.success("/", "修改成功");
	}
}
