package com.wipro.ta.samplebank.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
class AccountRepository {

	private static Map<String, Account> accounts = new ConcurrentHashMap<String, Account>();

	static List<Account> allAccounts() {
		return new ArrayList<Account>(accounts.values());
	}

	static Account findAccount(String ownerCpf) {
		if (accounts.containsKey(ownerCpf)) {
			return accounts.get(ownerCpf);
		}

		return null;
	}

	static void deleteAccount(Account c) {
		if (accounts.containsValue(c)) {

			Iterator<Entry<String, Account>> it = accounts.entrySet().iterator();

			while (it.hasNext()) {

				Entry<String, Account> item = it.next();

				if (item.getKey().equals(c.getOwnerCpf())) {
					it.remove();
				}
			}
		}
	}

	static void addAccount(Account c) {
		accounts.put(String.valueOf(c.getOwnerCpf()), c);
	}

	static void clearAccounts() {
		allAccounts().clear();
	}
}