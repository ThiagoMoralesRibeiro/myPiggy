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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

}
