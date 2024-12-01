package com.tucandeira.myPiggy.dao;

import com.tucandeira.myPiggy.model.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionDao{
	void save(Transaction transaction);
	Transaction findById(UUID transactionId);
	List<Transaction> findAll();
	void update(Transaction transaction);
	void delete(UUID transactionId);
}
