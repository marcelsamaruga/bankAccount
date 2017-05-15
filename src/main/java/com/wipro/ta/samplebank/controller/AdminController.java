package com.wipro.ta.samplebank.controller;

import javax.servlet.http.HttpServletResponse;

import com.wipro.ta.samplebank.controller.api.AccountController;
import com.wipro.ta.samplebank.domain.Account;
import com.wipro.ta.samplebank.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@Autowired
	private AccountController accountController;
	
	@RequestMapping(value = {"/addAccount"}, method = RequestMethod.GET)
	public String rootAddAccount(Model model) {
		model.addAttribute("account",  new Account(""));
		return "addAccount";
	}
	
	@RequestMapping(value = {"/addAccount"}, method = RequestMethod.POST)
	public String saveAccount(Account account, HttpServletResponse httpResponse, Model model) {
		String numericCpf = account.getOwnerCpf().replaceAll("[\\.-]", "");
		ResponseDTO<Account> responseDTO = accountController.createAccount(numericCpf, httpResponse);
		model.addAttribute("responseDTO", responseDTO);
				
		return rootAddAccount(model);
	}
}