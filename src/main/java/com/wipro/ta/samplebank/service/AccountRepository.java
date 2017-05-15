package com.wipro.ta.samplebank.service;

import com.wipro.ta.samplebank.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Service
class AccountRepository {
	
	private static Map<String, Account> accounts = new ConcurrentHashMap<String, Account>();
	
	static Account findAccount(String ownerCpf){
		if(accounts.containsKey(ownerCpf)){
			return accounts.get(ownerCpf);
		}
		
		return null;
	}
	
	static void deleteAccount(Account c){
		if(accounts.containsValue(c)){
			
			Iterator<Entry<String, Account>> it = accounts.entrySet().iterator();
			
			while(it.hasNext()) {
				
				Entry<String, Account> item = it.next();
				
				if(item.getKey().equals(c.getOwnerCpf())) {
					it.remove();
				}
			}
		}
	}
	
	static void addAccount(Account c){
		accounts.put(String.valueOf(c.getOwnerCpf()), c);
	}
}