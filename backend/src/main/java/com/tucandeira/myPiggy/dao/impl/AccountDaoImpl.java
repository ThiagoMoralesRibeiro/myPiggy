package com.tucandeira.myPiggy.dao.impl;

import com.tucandeira.myPiggy.model.Account;
import com.tucandeira.myPiggy.model.User;
import com.tucandeira.myPiggy.dao.AccountDao;
import com.tucandeira.myPiggy.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class AccountDaoImpl implements AccountDao {
  private final DbConnection dbConnection;

  public AccountDaoImpl() {
    dbConnection = new DbConnection();
  }

  @Override
  public void save(Account account) {
    String sql = "INSERT INTO account (user_id, balance_in_cents, account_type, account_number, branch_number) VALUES (?, ?, ?, ?, ?)";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      // UUID accountId = UUID.randomUUID();
      // account.setId(accountId);

      stmt.setObject(1, account.getUser().getId());
      stmt.setInt(2, account.getBalanceInCents());
      stmt.setString(3, account.getAccountType());
      stmt.setString(4, account.getAccountNumber());
      stmt.setString(5, account.getBranchNumber());

      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Account findByAccountNumber(String accountNumber) {
    String sql = """
            SELECT a.id AS account_id, a.*, u.id AS user_id, u.name AS user_name, u.email AS user_email
            FROM account a
            JOIN users u ON a.user_id = u.id
            WHERE a.account_number = ?
        """;
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, accountNumber);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        User user = new User(
            result.getInt("user_id"),
            result.getString("user_name"),
            result.getString("user_email"));

        return new Account(
            UUID.fromString(result.getString("account_id")),
            user,
            result.getInt("balance_in_cents"),
            result.getString("account_type"),
            result.getString("account_number"),
            result.getString("branch_number"));

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public List<Account> findAll() {
    List<Account> accounts = new ArrayList<>();
    String sql = """
            SELECT a.id AS account_id, a.*, u.id AS user_id, u.name AS user_name, u.email AS user_email
            FROM account a
            JOIN users u ON a.user_id = u.id
        """;
    try {

      Connection conn = dbConnection.getConnection();

      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        User user = new User(
            result.getInt("user_id"),
            result.getString("user_name"),
            result.getString("user_email"));

        Account account = new Account(
            UUID.fromString(result.getString("account_id")),
            user,
            result.getInt("balance_in_cents"),
            result.getString("account_type"),
            result.getString("account_number"),
            result.getString("branch_number"));
        accounts.add(account);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return accounts;
  }

  public Account findByUserId(int userId) {
    String sql = """
            SELECT a.id AS account_id, a.*, u.id AS user_id, u.name AS user_name, u.email AS user_email
            FROM account a
            JOIN users u ON a.user_id = u.id
            WHERE a.user_id = ?
        """;
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, userId);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        User user = new User(
            result.getInt("user_id"),
            result.getString("user_name"),
            result.getString("user_email"));

        return new Account(
            UUID.fromString(result.getString("account_id")),
            user,
            result.getInt("balance_in_cents"),
            result.getString("account_type"),
            result.getString("account_number"),
            result.getString("branch_number"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void update(Account account) {
    String sql = "UPDATE account SET balance_in_cents = ?, account_type = ? WHERE id = ?";

    try {

      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setInt(1, account.getBalanceInCents());
      stmt.setString(2, account.getAccountType());
      stmt.setObject(3, account.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(String accountNumber) {
    String sql = "DELETE FROM account WHERE account_number = ?";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, accountNumber);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
