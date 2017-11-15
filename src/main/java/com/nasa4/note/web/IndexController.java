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
	public String index(Model model, @PageableDefault(value = 5) Pageable pageable) {
		model.addAttribute("ps", noteService.findAll(pageable, keyword));
		return "index";
	}
	
	@GetMapping("/index/newest")
	public String newest(Model model, @PageableDefault(value = 5, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("ps", noteService.findAll(pageable, keyword));
		return "index";
	}
	
	@GetMapping("/comment")
	public String comment(Model model, @PageableDefault(value = 10, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("ps", commentService.findAll(pageable));
		return "comment";
	}
	
	@GetMapping("/my")
	public String mySpace(Model model, @PageableDefault(value = 10, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("ps", noteService.findByUserId(pageable, sessionUserId, reload));
		return "my";
	}
	
	/*@GetMapping("/hot")
	public Page<Note> hot(Model model, @PageableDefault(value = 5, sort = { "createTime" }, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Note> notes = noteService.findAll(pageable, keyword);
		Collections.sort(notes.getContent(), new Comparator<Note>(){

			
			 * int compare(Student o1, Student o2) 返回一个基本类型的整型，
			 * 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等，
			 * 返回正数表示：o1大于o2。
			 
			public int compare(Note o1, Note o2) {
			
				//按照学生的年龄进行升序排列
				if(o1.getComments().size() < o2.getComments().size()){
					return 1;
				}
				if(o1.getComments().size() == o2.getComments().size()){
					return 0;
				}
				return -1;
			}
		}); 
		return notes;
	}*/
	
}
