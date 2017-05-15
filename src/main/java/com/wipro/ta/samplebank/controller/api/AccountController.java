package com.wipro.ta.samplebank.controller.api;

import javax.servlet.http.HttpServletResponse;

import com.wipro.ta.samplebank.domain.Account;
import com.wipro.ta.samplebank.response.ResponseDTO;
import com.wipro.ta.samplebank.response.ResponseMessage;
import com.wipro.ta.samplebank.service.AccountService;
import com.wipro.ta.samplebank.validator.BusinessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> createAccount(
			@RequestParam(required = false) String ownerCpf,
			HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf)) {
			if(accountService.getAccount(ownerCpf) == null) {
				if (accountService.createAccount(ownerCpf)) {
	
					responseDTO.setData(accountService.getAccount(ownerCpf));
					responseDTO.setMessage(ResponseMessage.SUCCESS);
	
				} else {
					responseDTO.setMessage(ResponseMessage.OPERATION_ERROR);
				}
				
			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_ALREADY_EXISTS);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());

		return responseDTO;
	}

	@RequestMapping(value = "/{ownerCpf}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> getAccount(@PathVariable String ownerCpf,
			HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf)) {

			Account account = accountService.getAccount(ownerCpf);

			if (account != null) {
				responseDTO.setData(account);
				responseDTO.setMessage(ResponseMessage.SUCCESS);
			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_NOT_FOUND);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());

		return responseDTO;
	}

	@RequestMapping(value = "/{ownerCpf}/deposit", method = RequestMethod.PUT, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> makeDeposit(@PathVariable String ownerCpf,
			@RequestParam String value, HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf)) {
			if (accountService.getAccount(ownerCpf) != null) {
				if (BusinessValidator.isAmmountfValid(value)) {

					accountService.makeDeposit(ownerCpf, value);
					responseDTO.setMessage(ResponseMessage.SUCCESS);

				} else {
					responseDTO.setMessage(ResponseMessage.INVALID_AMMOUNT);
				}

			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_NOT_FOUND);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());

		return responseDTO;
	}

	@RequestMapping(value = "/{ownerCpf}/withdraw", method = RequestMethod.PUT, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> makeWithdraw(@PathVariable String ownerCpf,
			@RequestParam String value, HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf)) {
			if (accountService.getAccount(ownerCpf) != null) {
				if (BusinessValidator.isAmmountfValid(value)) {

					accountService.makeWithdraw(ownerCpf, value);
					responseDTO.setMessage(ResponseMessage.SUCCESS);

				} else {
					responseDTO.setMessage(ResponseMessage.INVALID_AMMOUNT);
				}

			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_NOT_FOUND);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());

		return responseDTO;
	}

	@RequestMapping(value = "/{ownerCpf}/transfer", method = RequestMethod.PUT, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> makeTransfer(@PathVariable String ownerCpf,
			@RequestParam String targetCpf, @RequestParam String value,
			HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf) && BusinessValidator.isCpfValid(targetCpf)) {
			if (accountService.getAccount(ownerCpf) != null
					&& accountService.getAccount(targetCpf) != null) {
				
				if (BusinessValidator.isAmmountfValid(value)) {

					accountService.makeTransfer(ownerCpf, targetCpf, value);
					responseDTO.setMessage(ResponseMessage.SUCCESS);

				} else {
					responseDTO.setMessage(ResponseMessage.INVALID_AMMOUNT);
				}

			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_NOT_FOUND);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());

		return responseDTO;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE, produces = { "application/json" })
	@ResponseBody
	public ResponseDTO<Account> deleteAccount(
			@RequestParam(required = false) String ownerCpf,
			HttpServletResponse response) {

		ResponseDTO<Account> responseDTO = new ResponseDTO<>();

		if (BusinessValidator.isCpfValid(ownerCpf)) {
			if (accountService.getAccount(ownerCpf) != null) {
				if (accountService.deleteAccount(ownerCpf)) {
					responseDTO.setMessage(ResponseMessage.SUCCESS);
				} else {
					responseDTO.setMessage(ResponseMessage.OPERATION_ERROR);
				}
				
			} else {
				responseDTO.setMessage(ResponseMessage.ACCOUNT_NOT_FOUND);
			}

		} else {
			responseDTO.setMessage(ResponseMessage.INVALID_CPF);
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return responseDTO;
	}
}