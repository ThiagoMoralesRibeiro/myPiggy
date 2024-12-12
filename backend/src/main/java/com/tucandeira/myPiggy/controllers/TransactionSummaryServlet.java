package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


import com.tucandeira.myPiggy.dao.TransactionDao;
import com.tucandeira.myPiggy.dao.impl.TransactionDaoImpl;

import java.util.UUID;
import java.io.IOException;

@WebServlet("/transaction-summary/*")

public class TransactionSummaryServlet extends HttpServlet {
  private TransactionDao transactionDao;
  

  public void init() throws ServletException {
    transactionDao = new TransactionDaoImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();

    if (pathInfo != null && !pathInfo.isEmpty()) {
      String accountIdString = pathInfo.substring(1);
      UUID accountId = UUID.fromString(accountIdString);
      try {
        double totalCredit = transactionDao.getTotalCredit(accountId);
        double totalDebit = transactionDao.getTotalDebit(accountId);
       
        response.getWriter().write("{\"totalCredit\": " + totalCredit + ", \"totalDebit\": " + totalDebit + "}");
        
        response.setStatus(HttpServletResponse.SC_OK);
        
      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"Failed to returned total transactions.\"}");
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \" AccountID is required.\"}");

    }
  }
}
