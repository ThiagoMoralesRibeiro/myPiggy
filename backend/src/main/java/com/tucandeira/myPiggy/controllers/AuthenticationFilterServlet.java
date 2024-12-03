/*package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet("/*")
public class AuthenticationFilterServlet implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession(false);

    String loginUri = req.getContextPath() + "/login";
    String registerUri = req.getContextPath() + "/register";
    boolean loggedIn = (session != null && session.getAttribute("userId") != null);
    boolean publicRequest = req.getRequestURI().equals(loginUri) || req.getRequestURI().equals(registerUri);

    if (loggedIn || publicRequest) {
      filter.doFilter(req, res);
    } else {
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
      res.getWriter().write("{\"error\": \"Unauthorized access, please log in\"}");
    }
  }
}*/
