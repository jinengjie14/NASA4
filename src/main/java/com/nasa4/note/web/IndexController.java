package com.nasa4.note.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController {

	@GetMapping({"/", "index"})
	public String index(Model model) {
		getUrl(model);
		return "index";
	}
	
}
