package com.tucandeira.myPiggy.dao;

import com.tucandeira.myPiggy.model.Category;
import java.util.List;

public interface CategoryDao{
	void save(Category category);
	Category findById(int categoryId);
	List<Category> findAll();
	void update(Category category);
	void delete(int categoryId);
}
