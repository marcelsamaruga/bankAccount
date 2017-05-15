package com.wipro.ta.samplebank.controller;

import javax.servlet.http.HttpSession;

import com.wipro.ta.samplebank.auth.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class AuthenticationController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Login login, Model model) {
		if (!"admin".equals(login.getUsername()) || !"admin".equals(login.getPassword())) {
			model.addAttribute("error", new Error("Invalid Login"));
			return "index";
		}
		
		model.addAttribute("login", login);

		return "redirect:home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doLogout(HttpSession session) {
		
		session.invalidate();

		return "redirect:index";
	}
}