package com.wipro.ta.samplebank.controller;

import com.wipro.ta.samplebank.auth.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootWebController {

	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String rootRedirect() {		
		return "index";
	}
	
	@ModelAttribute("login")
	public Login getLogin() {
		return new Login();
	}
}