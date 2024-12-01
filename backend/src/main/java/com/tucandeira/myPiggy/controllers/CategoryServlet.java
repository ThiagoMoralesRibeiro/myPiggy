package com.tucandeira.myPiggy.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tucandeira.myPiggy.dao.CategoryDao;
import com.tucandeira.myPiggy.dao.impl.CategoryDaoImpl;
import com.tucandeira.myPiggy.model.Category;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/category/*")
public class CategoryServlet extends HttpServlet {
  private CategoryDao categoryDao;
  private Gson gson = new Gson();

  @Override
  public void init() throws ServletException {
    categoryDao = new CategoryDaoImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo(); // Me permitira pegar o complemento da rota

    if (pathInfo == null || pathInfo.equals("/")) {
      getAll(response);
    } else {
      String categoryNumber = pathInfo.substring(1);
      getById(response, categoryNumber);
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

      Category category = gson.fromJson(jsonData, Category.class);

      categoryDao.save(category);

      response.setStatus(HttpServletResponse.SC_CREATED);
      response.getWriter().write(gson.toJson("category created successfully."));
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

    Category category = gson.fromJson(jsonData, Category.class);
  

    categoryDao.update(category);

    response.setStatus(HttpServletResponse.SC_CREATED);
    response.getWriter().write(gson.toJson("category updated successfully."));

  }
  
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();

    if (pathInfo != null && !pathInfo.isEmpty()) {
      String categoryId = pathInfo.substring(1);

      try {
        categoryDao.delete(Integer.parseInt(categoryId));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"message\": \"category deleted successfully.\"}");

      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"Failed to delete the category.\"}");
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"User ID is required.\"}");

    }

  }

  private void getAll(HttpServletResponse response) throws IOException {
    List<Category> category = categoryDao.findAll();

    String categoryJson = gson.toJson(category);

    PrintWriter out = response.getWriter();
    out.print(categoryJson);
    out.flush();
  }

  private void getById(HttpServletResponse response, String categoryNumber) throws IOException {
    Category category = categoryDao.findById(Integer.parseInt(categoryNumber));
    String categoryJson = gson.toJson(category);

    PrintWriter out = response.getWriter();
    out.print(categoryJson);
    out.flush();
  }

}
