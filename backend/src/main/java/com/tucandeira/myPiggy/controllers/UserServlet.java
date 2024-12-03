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
import com.tucandeira.myPiggy.dao.impl.UserDaoImpl;
import com.tucandeira.myPiggy.model.User;
import com.tucandeira.myPiggy.dao.UserDao;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
  private UserDao userDao;
  private Gson gson = new Gson();

  @Override
  public void init() throws ServletException {
    userDao = new UserDaoImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo(); // Me permitira pegar o complemento da rota

    if (pathInfo == null || pathInfo.equals("/")) {
      getAllUsers(response);
    } else {
      // PrintWriter out = response.getWriter();
      String userIdString = pathInfo.substring(1);
      int userId = Integer.parseInt(userIdString);
      // out.println(userId);
      getUserById(response, userId);
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

      User user = gson.fromJson(jsonData, User.class);

      userDao.save(user);

      response.setStatus(HttpServletResponse.SC_CREATED);
      response.getWriter().write(gson.toJson("User created successfully."));
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

    User user = gson.fromJson(jsonData, User.class);

    userDao.update(user);

    response.setStatus(HttpServletResponse.SC_CREATED);
    response.getWriter().write(gson.toJson("User updated successfully."));

  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();

    if (pathInfo != null && !pathInfo.isEmpty()) {
      String userIdString = pathInfo.substring(1);
      int userId = Integer.parseInt(userIdString);

      try {
        userDao.delete(userId);
        response.setStatus(HttpServletResponse.SC_OK);
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

  private void getAllUsers(HttpServletResponse response) throws IOException {
    List<User> users = userDao.findAll();

    String userJson = gson.toJson(users);

    PrintWriter out = response.getWriter();
    out.print(userJson);
    out.flush();
  }
  
  private void getUserById(HttpServletResponse response, int userId) throws IOException {
    User user = userDao.findById(userId);
    String userJson = gson.toJson(user);

    PrintWriter out = response.getWriter();
    out.print(userJson);
    out.flush();
  }

}
