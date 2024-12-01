package com.tucandeira.myPiggy.dao;

import com.tucandeira.myPiggy.model.Piggybank;
import java.util.List;

public interface PiggyBankDao{
	void save(Piggybank piggybank);
	Piggybank findById(String piggybankId);
	List<Piggybank> findAll();
	void update(Piggybank piggybank);
	void delete(String piggybankId);
}
