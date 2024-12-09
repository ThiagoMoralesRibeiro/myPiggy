package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tucandeira.myPiggy.dao.impl.AccountDaoImpl;
import com.tucandeira.myPiggy.dao.AccountDao;
import com.tucandeira.myPiggy.model.Account;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {
  private AccountDao accountDao;
  private Gson gson = new Gson();

  @Override
  public void init() throws ServletException {
    accountDao = new AccountDaoImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo(); // Me permitira pegar o complemento da rota

    if (pathInfo == null || pathInfo.equals("/")) {
      getAllAccounts(response);
    } else {
      String accountNumber = pathInfo.substring(1);
      getAccountByNumber(response, accountNumber);
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try {
      StringBuilder jsonBuffer = new StringBuilder();
      BufferedReader reader = request.getReader();

      String line;
      while ((line = reader.readLine()) != null) {
        jsonBuffer.append(line);
      }

      String jsonData = jsonBuffer.toString();

      Account account = gson.fromJson(jsonData, Account.class);

      accountDao.save(account);

      response.setStatus(HttpServletResponse.SC_CREATED);
      response.getWriter().write(gson.toJson("Account created successfully."));
    } catch (JsonSyntaxException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write(gson.toJson("Invalid JSON format."));
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write(gson.toJson("An unexpected error occurred: " + e.getMessage()));
    }
  }
  
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    StringBuilder jsonBuffer = new StringBuilder();
    BufferedReader reader = request.getReader();

    String line;
    while ((line = reader.readLine()) != null) {
      jsonBuffer.append(line);
    }

    String jsonData = jsonBuffer.toString();

    Account account = gson.fromJson(jsonData, Account.class);

    accountDao.update(account);

    response.setStatus(HttpServletResponse.SC_CREATED);
    response.getWriter().write(gson.toJson("Account updated successfully."));

  }
  
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();

    if (pathInfo != null && !pathInfo.isEmpty()) {
      String accountNumber = pathInfo.substring(1);

      try {
        accountDao.delete(accountNumber);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"message\": \"Account deleted successfully.\"}");

      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"Failed to delete the account.\"}");
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"User ID is required.\"}");

    }

  }

  private void getAllAccounts(HttpServletResponse response) throws IOException {
    List<Account> accounts = accountDao.findAll();

    String accountJson = gson.toJson(accounts);

    PrintWriter out = response.getWriter();
    out.print(accountJson);
    out.flush();
  }

  private void getAccountByNumber(HttpServletResponse response, String accountNumber) throws IOException {
    Account account = accountDao.findByAccountNumber(accountNumber);
    String accountJson = gson.toJson(account);

    PrintWriter out = response.getWriter();
    out.print(accountJson);
    out.flush();
  }

}
