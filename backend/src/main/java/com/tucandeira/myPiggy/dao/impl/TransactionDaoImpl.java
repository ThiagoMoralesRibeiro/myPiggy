package com.tucandeira.myPiggy.dao.impl;

import com.tucandeira.myPiggy.dao.TransactionDao;
import com.tucandeira.myPiggy.model.Account;
import com.tucandeira.myPiggy.model.Category;
import com.tucandeira.myPiggy.model.Transaction;
import com.tucandeira.myPiggy.utils.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class TransactionDaoImpl implements TransactionDao {
  private final DbConnection dbConnection;

  public TransactionDaoImpl() {
    dbConnection = new DbConnection();
  }

  @Override
  public void save(Transaction transaction) {
    String sql = "INSERT INTO transactions (account_id, category_id, transaction_type, amount_in_cents, description, transaction_date, is_recurring, recurrence_period) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      // UUID accountId = UUID.randomUUID();
      // account.setId(accountId);

      stmt.setObject(1, transaction.getAccount().getId());
      stmt.setInt(2, transaction.getCategory().getId());
      stmt.setObject(3, transaction.getTransactionType().name());
      stmt.setInt(4, transaction.getAmountInCents());
      stmt.setString(5, transaction.getDescription());
      stmt.setDate(6, java.sql.Date.valueOf(transaction.getTransactionDate()));
      stmt.setBoolean(7, transaction.isRecurring());
      if (transaction.getRecurrencePeriod() != null) {
        stmt.setString(8, transaction.getRecurrencePeriod().name());
      } else {
        stmt.setNull(8, java.sql.Types.VARCHAR);
      }
      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Transaction findById(UUID transactionId) {
    String sql = """
        SELECT
            t.id AS transaction_id,
            t.amount_in_cents,
            t.transaction_type,
            t.description AS transaction_description,
            t.transaction_date,
            t.is_recurring,
            t.recurrence_period,
            a.account_number,
            a.branch_number,
            a.balance_in_cents,
            a.account_type,
            c.id AS category_id,
            c.name AS category_name,
            c.description AS category_description
            FROM transactions t
            JOIN account a ON t.account_id = a.id
            JOIN categories c ON t.category_id = c.id
            WHERE t.id = ?
        """;

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setObject(1, transactionId);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {

        Date sqlDate = result.getDate("transaction_date");

        LocalDate birthDate = sqlDate != null ? sqlDate.toLocalDate() : null;

        Category category = new Category(
            result.getInt("category_id"),
            result.getString("category_name"),
            result.getString("category_description"));

        Account account = new Account(
            result.getInt("balance_in_cents"),
            result.getString("account_type"),
            result.getString("account_number"),
            result.getString("branch_number"));

        return new Transaction(
            UUID.fromString(result.getString("transaction_id")),
            account,
            Transaction.TransactionType.valueOf(result.getString("transaction_type").toLowerCase()),
            result.getInt("amount_in_cents"),
            result.getString("transaction_description"),
            birthDate,
            category,
            result.getBoolean("is_recurring"),
            result.getString("recurrence_period") != null
                ? Transaction.Recurrency.valueOf(result.getString("recurrence_period").toLowerCase())
                : null);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public List<Transaction> findAll() {
    List<Transaction> transactions = new ArrayList<>();
    String sql = """
            SELECT
            t.id AS transaction_id,
            t.amount_in_cents,
            t.transaction_type,
            t.description AS transaction_description,
            t.transaction_date,
            t.is_recurring,
            t.recurrence_period,
            a.id AS account_id,
            a.account_number,
            a.branch_number,
            a.balance_in_cents,
            a.account_type,
            c.id AS category_id,
            c.name AS category_name,
            c.description AS category_description
            FROM transactions t
            JOIN account a ON t.account_id = a.id
            JOIN categories c ON t.category_id = c.id
        """;
    try {
      Connection conn = dbConnection.getConnection();

      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        Date sqlDate = result.getDate("transaction_date");

        LocalDate birthDate = sqlDate != null ? sqlDate.toLocalDate() : null;

        Category category = new Category(
            result.getInt("category_id"),
            result.getString("category_name"),
            result.getString("category_description"));

        Account account = new Account(
            result.getInt("balance_in_cents"),
            result.getString("account_type"),
            result.getString("account_number"),
            result.getString("branch_number"));

        Transaction transaction = new Transaction(
            UUID.fromString(result.getString("transaction_id")),
            account,
            Transaction.TransactionType.valueOf(result.getString("transaction_type").toLowerCase()),
            result.getInt("amount_in_cents"),
            result.getString("transaction_description"),
            birthDate,
            category,
            result.getBoolean("is_recurring"),
            result.getString("recurrence_period") != null
                ? Transaction.Recurrency.valueOf(result.getString("recurrence_period").toLowerCase())
                : null);

        transactions.add(transaction);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return transactions;
  }

  @Override
  public void update(Transaction transaction) {
    String sql = """
        UPDATE transactions SET
            transaction_type = ?,
            amount_in_cents = ?,
            description = ?,
            transaction_date = ?,
            category_id = ?,
            is_recurring = ?,
            recurrence_period = ?
            WHERE id = ?
        """;

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, transaction.getTransactionType().name().toLowerCase());
      stmt.setInt(2, transaction.getAmountInCents());
      stmt.setString(3, transaction.getDescription());
      stmt.setObject(4, transaction.getTransactionDate());
      stmt.setObject(5, transaction.getCategory().getId());
      stmt.setBoolean(6, transaction.isRecurring());
      stmt.setString(7,
          transaction.getRecurrencePeriod() != null ? transaction.getRecurrencePeriod().name().toLowerCase() : null);
      stmt.setObject(8, transaction.getId());

      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(UUID transactionId) {
    String sql = "DELETE FROM transactions WHERE id = ?";

    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setObject(1, transactionId);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public double getTotalCredit(UUID accountId) {
    String sql = "SELECT SUM(amount_in_cents) AS total FROM transactions WHERE transaction_type = 'credit' AND account_id = ?";
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setObject(1, accountId);
      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        return result.getDouble("total") / 100.0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public double getTotalDebit(UUID accountId) {
   String sql = "SELECT SUM(amount_in_cents) AS total FROM transactions WHERE transaction_type = 'debit' AND account_id = ?";
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setObject(1, accountId);
      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        return result.getDouble("total") / 100.0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0; 
  }

}
