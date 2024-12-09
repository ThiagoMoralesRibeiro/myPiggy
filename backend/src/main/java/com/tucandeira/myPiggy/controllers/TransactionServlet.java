package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tucandeira.myPiggy.dao.TransactionDao;
import com.tucandeira.myPiggy.dao.impl.TransactionDaoImpl;
import com.tucandeira.myPiggy.model.Transaction;

import java.util.List;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/transaction/*")
public class TransactionServlet extends HttpServlet {
  private TransactionDao transactionDao;
  private Gson gson = new Gson();

  @Override
  public void init() throws ServletException {
    transactionDao = new TransactionDaoImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo(); // Me permitira pegar o complemento da rota

    if (pathInfo == null || pathInfo.equals("/")) {
      getAll(response);
    } else {
      String transactionId = pathInfo.substring(1);
      getById(response, transactionId);
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

      Transaction transaction = gson.fromJson(jsonData, Transaction.class);

      transactionDao.save(transaction);

      response.setStatus(HttpServletResponse.SC_CREATED);
      response.getWriter().write(gson.toJson("Transaction created successfully."));
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

    Transaction transaction = gson.fromJson(jsonData, Transaction.class);
  

    transactionDao.update(transaction);

    response.setStatus(HttpServletResponse.SC_CREATED);
    response.getWriter().write(gson.toJson("transaction updated successfully."));

  }
  
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();

    if (pathInfo != null && !pathInfo.isEmpty()) {
      String transactionId = pathInfo.substring(1);

      try {
        transactionDao.delete(UUID.fromString(transactionId));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"message\": \"Transaction deleted successfully.\"}");

      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"Failed to delete the Transaction.\"}");
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"User ID is required.\"}");

    }

  }

  private void getAll(HttpServletResponse response) throws IOException {
    List<Transaction> transaction = transactionDao.findAll();

    String transactionJson = gson.toJson(transaction);

    PrintWriter out = response.getWriter();
    out.print(transactionJson);
    out.flush();
  }

  private void getById(HttpServletResponse response, String transactionId) throws IOException {
    Transaction transaction = transactionDao.findById(UUID.fromString(transactionId));
    String transactionJson = gson.toJson(transaction);

    PrintWriter out = response.getWriter();
    out.print(transactionJson);
    out.flush();
  }

}
