package com.tucandeira.myPiggy.controllers;

import com.tucandeira.myPiggy.dao.AccountDao;
import com.tucandeira.myPiggy.dao.impl.AccountDaoImpl;
import com.tucandeira.myPiggy.model.Account;
import com.tucandeira.myPiggy.utils.DbConnection;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private AccountDao accountDao;
  private static final String PEPPER = "StandByMeGuys";

  @Override
  public void init() throws ServletException {
    accountDao = new AccountDaoImpl();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Account accountInfo = authenticateUser(email, password);

      if (accountInfo != null) {
        setupSessionAndCookies(request, response, accountInfo);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"message\": \"Login successful\"}");
      } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"Invalid email or password\"}");
      }
    } catch (IOException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\": \"User not found\"}");
    } catch (Exception e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("{\"error\": \"Server error, please try again later\"}");
    }
  }

  private Account authenticateUser(String email, String password) throws Exception {
    try (Connection conn = new DbConnection().getConnection()) {
      String sql = "SELECT * FROM users WHERE email = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, email);
      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        String storedHash = result.getString("password");
        String pepperedPassword = password + PEPPER;

        BCrypt.Result resultCrypt = BCrypt.verifyer().verify(pepperedPassword.toCharArray(), storedHash);

        if (resultCrypt.verified) {
          int userId = result.getInt("id");
          return accountDao.findByUserId(userId);
        } else {
          return null; // Senha incorreta
        }
      } else {
        return null; // Usuario nao encontrado
      }
    }
  }

  private void setupSessionAndCookies(HttpServletRequest request, HttpServletResponse response, Account accountInfo) {
    HttpSession session = request.getSession();
    session.setAttribute("email", accountInfo.getUser().getEmail());
    session.setAttribute("userId", accountInfo.getUser().getId());
    session.setAttribute("userName", accountInfo.getUser().getName());
    session.setAttribute("accountId", accountInfo.getId());
    session.setAttribute("accountNumber", accountInfo.getAccountNumber());
    session.setAttribute("branchNumber", accountInfo.getBranchNumber());
    session.setAttribute("balanceInCents", accountInfo.getBalanceInCents());

    Cookie jsessionCookie = new Cookie("JSESSIONID", session.getId());
    jsessionCookie.setMaxAge(-1);
    jsessionCookie.setHttpOnly(true);
    jsessionCookie.setSecure(true);
    jsessionCookie.setPath("/");
    response.addCookie(jsessionCookie);
  }
}

/*
 * @WebServlet("/login")
 * public class LoginServlet extends HttpServlet {
 * 
 * private AccountDao accountDao;
 * private static final String PEPPER = "StandByMeGuys";
 * 
 * @Override
 * public void init() throws ServletException {
 * accountDao = new AccountDaoImpl();
 * }
 * 
 * @Override
 * public void doPost(HttpServletRequest request, HttpServletResponse response)
 * throws ServletException, IOException {
 * String email = request.getParameter("email");
 * String password = request.getParameter("password");
 * 
 * try {
 * Account accountInfo = authenticateUser(email, password);
 * 
 * if (accountInfo != null) {
 * setupSessionAndCookies(request, response, accountInfo);
 * response.setStatus(HttpServletResponse.SC_OK);
 * response.getWriter().write("{\"message\": \"Login successful\"}");
 * } else {
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * response.getWriter().write("{\"error\": \"Invalid email or password\"}");
 * }
 * } catch (UserNotFoundException e) {
 * response.setStatus(HttpServletResponse.SC_NOT_FOUND);
 * response.getWriter().write("{\"error\": \"User not found\"}");
 * } catch (Exception e) {
 * e.printStackTrace();
 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 * response.getWriter().
 * write("{\"error\": \"Server error, please try again later\"}");
 * }
 * }
 * 
 * private Account authenticateUser(String email, String password) throws
 * Exception {
 * try (Connection conn = new DbConnection().getConnection()) {
 * String sql = "SELECT * FROM users WHERE email = ?";
 * PreparedStatement stmt = conn.prepareStatement(sql);
 * stmt.setString(1, email);
 * ResultSet result = stmt.executeQuery();
 * 
 * if (result.next()) {
 * String storedHash = result.getString("password");
 * String pepperedPassword = password + PEPPER;
 * 
 * BCrypt.Result resultCrypt =
 * BCrypt.verifyer().verify(pepperedPassword.toCharArray(), storedHash);
 * 
 * if (resultCrypt.verified) {
 * int userId = result.getInt("id");
 * return accountDao.findByUserId(userId);
 * } else {
 * return null; // Senha incorreta
 * }
 * } else {
 * throw new UserNotFoundException("User with email " + email + " not found.");
 * }
 * }
 * }
 * 
 * private void setupSessionAndCookies(HttpServletRequest request,
 * HttpServletResponse response, Account accountInfo) {
 * HttpSession session = request.getSession();
 * session.setAttribute("email", accountInfo.getUser().getEmail());
 * session.setAttribute("userId", accountInfo.getUser().getId());
 * session.setAttribute("userName", accountInfo.getUser().getName());
 * session.setAttribute("accountId", accountInfo.getId());
 * session.setAttribute("accountNumber", accountInfo.getAccountNumber());
 * session.setAttribute("branchNumber", accountInfo.getBranchNumber());
 * session.setAttribute("balanceInCents", accountInfo.getBalanceInCents());
 * 
 * Cookie jsessionCookie = new Cookie("JSESSIONID", session.getId());
 * jsessionCookie.setMaxAge(-1);
 * jsessionCookie.setHttpOnly(true);
 * jsessionCookie.setSecure(true);
 * jsessionCookie.setPath("/");
 * response.addCookie(jsessionCookie);
 * }
 * }
 */
