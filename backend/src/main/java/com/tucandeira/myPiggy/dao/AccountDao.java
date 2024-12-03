package com.tucandeira.myPiggy.dao;

import com.tucandeira.myPiggy.model.Account;
import java.util.List;

public interface AccountDao{
	void save(Account account);
	Account findByAccountNumber(String accountNumber);
  Account findByUserId(int userId);
	List<Account> findAll();
	void update(Account account);
	void delete(String accountNumber);
}
