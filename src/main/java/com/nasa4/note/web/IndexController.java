package com.nasa4.note.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nasa4.note.service.CommentService;
import com.nasa4.note.service.NoteService;

@Controller
public class IndexController extends BaseController {
	@Autowired
	private NoteService noteService;
	@Autowired
	private CommentService commentService;

	@GetMapping({"/", "/index"})
	public String index(Model model, @PageableDefault(value = 8) Pageable pageable) {
		model.addAttribute("ps", noteService.findAll(pageable));
		return "index";
	}
	
	@GetMapping("/index/newest")
	public String newest(Model model, @PageableDefault(value = 8, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("ps", noteService.findAll(pageable));
		return "index";
	}
	
	@GetMapping("/comment")
	public String comment(Model model, @PageableDefault(value = 8, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("ps", commentService.findAll(pageable));
		return "comment";
	}
	
}
