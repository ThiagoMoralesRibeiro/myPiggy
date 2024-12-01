package com.tucandeira.myPiggy.dao.impl;

import com.tucandeira.myPiggy.model.User;
import com.password4j.Hash;
import com.password4j.Password;
import com.tucandeira.myPiggy.dao.UserDao;
import com.tucandeira.myPiggy.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
  private final DbConnection dbConnection;

  public UserDaoImpl() {
    dbConnection = new DbConnection();
  }

  @Override
  public void save(User user) {
    String sql = "INSERT INTO users (name, email, password, birth_date, phone_number, cpf, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      String pepper = "StandByMeGuys";
      Hash hash = Password.hash(new String(user.getPassword()) + pepper).withBcrypt();

      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.setString(3, hash.getResult());
      stmt.setDate(4, java.sql.Date.valueOf(user.getBirthDate()));
      stmt.setString(5, user.getPhoneNumber());
      stmt.setString(6, user.getCpf());
      stmt.setString(7, user.getAddress());

      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public User findById(int userId) {
    String sql = "SELECT * FROM users WHERE id = ?";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, userId);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        Date sqlDate = result.getDate("birth_date");

        LocalDate birthDate = sqlDate != null ? sqlDate.toLocalDate() : null;

        User user = new User(
            result.getString("name"),
            result.getString("email"),
            result.getString("password"),
            birthDate,
            result.getString("phone_number"),
            result.getString("cpf"),
            result.getString("address"));

        user.setId(result.getInt("id"));

        return user;

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM users";
    try {

      Connection conn = dbConnection.getConnection();

      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        Date sqlDate = result.getDate("birth_date");

        LocalDate birthDate = sqlDate != null ? sqlDate.toLocalDate() : null;

        User user = new User(
            result.getString("name"),
            result.getString("email"),
            result.getString("password"),
            birthDate,
            result.getString("phone_number"),
            result.getString("cpf"),
            result.getString("address"));

        user.setId(result.getInt("id"));

        users.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  @Override
  public void update(User user) {
    String sql = "UPDATE users SET name = ?, email = ?, birth_date = ?, phone_number = ?, cpf = ?, address= ? WHERE id = ?";

    try {

      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      
      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.setObject(3, user.getBirthDate());
      stmt.setObject(4, user.getPhoneNumber());
      stmt.setObject(5, user.getCpf());
      stmt.setObject(6, user.getAddress());
      stmt.setObject(7, user.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(int userId) {
    String sql = "DELETE FROM users WHERE id = ?";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, userId);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
