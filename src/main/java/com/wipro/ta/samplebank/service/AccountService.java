package com.wipro.ta.samplebank.service;

import java.math.BigDecimal;

import com.wipro.ta.samplebank.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	public boolean createAccount(String ownerCpf) {
		if (AccountRepository.findAccount(ownerCpf) == null) {
			Account c = new Account(ownerCpf);
			AccountRepository.addAccount(c);

			return true;
		}
		return false;
	}

	public Account getAccount(String ownerCpf) {
		return AccountRepository.findAccount(ownerCpf);
	}

	public void makeDeposit(String ownerCpf, String value) {
		Account c = AccountRepository.findAccount(ownerCpf);
		if (c != null) {
			c.deposit(new BigDecimal(value));
		}
	}

	public void makeWithdraw(String ownerCpf, String value) {
		Account c = AccountRepository.findAccount(ownerCpf);
		if (c != null) {
			BigDecimal amount = new BigDecimal(value);
			if (c.getBalance().compareTo(amount) >= 0) {
				c.withdraw(new BigDecimal(value));
			}
		}
	}

	public void makeTransfer(String originOwnerCpf, String targetOwnerCpf,
			String value) {
		Account origin = AccountRepository.findAccount(originOwnerCpf);
		Account target = AccountRepository.findAccount(targetOwnerCpf);

		if (origin != null && target != null) {
			BigDecimal amount = new BigDecimal(value);
			if (origin.getBalance().compareTo(amount) >= 0) {
				origin.withdraw(amount);
				target.deposit(amount);
			}
		}
	}

	public BigDecimal getAccountBalance(String ownerCpf) {
		Account c = AccountRepository.findAccount(ownerCpf);
		if (c != null) {
			return c.getBalance();
		}
		return null;
	}

	public boolean deleteAccount(String ownerCpf) {
		Account c = AccountRepository.findAccount(ownerCpf);
		if (c != null) {
			AccountRepository.deleteAccount(c);
			return true;
		}
		return false;
	}

}
