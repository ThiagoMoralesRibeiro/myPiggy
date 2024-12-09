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
          int userId = result.getInt("id"); // Validamos que o valor do ID estamos pegando
          //System.out.println("O valor do userId: " + userId);

          Account accountInfo = accountDao.findByUserId(userId);

          if (accountInfo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("userId", accountInfo.getUser().getId());
            session.setAttribute("userName", accountInfo.getUser().getName());
            session.setAttribute("accountId", accountInfo.getId());
            session.setAttribute("accountNumber", accountInfo.getAccountNumber());
            session.setAttribute("branchNumber", accountInfo.getBranchNumber());
            session.setAttribute("balanceInCents", accountInfo.getBalanceInCents());
            session.setAttribute("email", email); 
            Cookie jsessionCookie = new Cookie("JSESSIONID", session.getId());

            jsessionCookie.setMaxAge(-1);
            jsessionCookie.setHttpOnly(true);

            jsessionCookie.setSecure(true);
            jsessionCookie.setPath("/");
            response.addCookie(jsessionCookie);
          }

          response.setStatus(HttpServletResponse.SC_OK);
          response.getWriter().write("{\"message\": \"Login successful\"}");

        } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("{\"error\": \"Invalid email or password\"}");
        }
      } else {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        response.getWriter().write("{\"error\": \"User not found\"}");
      }
    } catch (Exception e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
      response.getWriter().write("{\"error\": \"Server error, please try again later\"}");
    }
  }
}
