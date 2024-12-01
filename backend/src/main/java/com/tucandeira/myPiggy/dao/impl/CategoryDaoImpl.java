package com.tucandeira.myPiggy.dao.impl;

import com.tucandeira.myPiggy.model.Category;
import com.tucandeira.myPiggy.dao.CategoryDao;
import com.tucandeira.myPiggy.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CategoryDaoImpl implements CategoryDao {
  private final DbConnection dbConnection;

  public CategoryDaoImpl() {
    dbConnection = new DbConnection();
  }

  @Override
  public void save(Category category) {
    String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      // UUID accountId = UUID.randomUUID();
      // account.setId(accountId);

      stmt.setString(1, category.getName());
      stmt.setString(2, category.getDescription());

      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Category findById(int categoryId) {
    String sql = "SELECT * FROM categories WHERE id = ? ";
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, categoryId);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        return new Category(
            result.getInt("id"),
            result.getString("name"),
            result.getString("description"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public List<Category> findAll() {
    List<Category> categories = new ArrayList<>();
    String sql = "SELECT * FROM categories";
    try {

      Connection conn = dbConnection.getConnection();

      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        Category category = new Category(
            result.getInt("id"),
            result.getString("name"),
            result.getString("description"));

        categories.add(category);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return categories;
  }

  @Override
  public void update(Category category) {
    String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";

    try {

      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, category.getName());
      stmt.setString(2, category.getDescription());
      stmt.setInt(3, category.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(int categoryId) {
    String sql = "DELETE FROM categories WHERE id = ?";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, categoryId);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
