package com.nasa4.note.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nasa4.note.service.CommentService;
import com.nasa4.note.utils.Result;
import com.nasa4.note.web.validform.CommentForm;

@Controller
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@PostMapping("/comment")
	public Result create(@Valid CommentForm commentForm) {
		commentService.save(sessionUserId, commentForm, reload);
		return Result.success("/note/"+commentForm.getNoteId(), "评论成功");
	}
}
