package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


@WebServlet("/session-info")
public class SessionInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            
            String email = (String) session.getAttribute("email");
            int userId = (int) session.getAttribute("userId");
            UUID accountId = (UUID) session.getAttribute("accountId");
            String accountNumber = (String) session.getAttribute("accountNumber");
            String branchNumber = (String) session.getAttribute("branchNumber");
             
            response.setContentType("application/json");
            response.getWriter().write(
                "{ \"email\": \"" + email + "\", " +
                "\"userId\": " + userId + ", " +
                "\"accountId\": \"" + accountId + "\", " +
                "\"accountNumber\": \"" + accountNumber + "\", " +
                "\"branchNumber\": \"" + branchNumber + "\" }"
            );
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"No active session found\"}");
        }
    }
}



/*@WebServlet("/session-info")
public class SessionInfoServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession(false);

    if (session != null) {

      String email = (String) session.getAttribute("email");
      int userId = (int) session.getAttribute("userId");
      int balanceInCents = (int) session.getAttribute("balanceInCents");
      String accountId = (String) session.getAttribute("accountId");
      String accountNumber = (String) session.getAttribute("accountNumber");
      String branchNumber = (String) session.getAttribute("branchNumber");

      response.setContentType("application/json");
      response.getWriter().write(
          "{ \"email\": \"" + email + "\", " +
              "\"userId\": " + userId + ", " +
              "\"balanceInCents\": " + balanceInCents + ", " +
              "\"accountId\": \"" + accountId + "\", " +
              "\"accountNumber\": \"" + accountNumber + "\", " +
              "\"branchNumber\": \"" + branchNumber + "\" }");
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("{\"error\": \"No active session found\"}");
    }
  }
}*/
