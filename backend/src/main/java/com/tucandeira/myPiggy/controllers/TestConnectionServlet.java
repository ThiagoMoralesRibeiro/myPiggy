
package com.tucandeira.myPiggy.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.tucandeira.myPiggy.utils.DbConnection;

@WebServlet("/testConnection")
public class TestConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Método que será chamado quando a URL /testConnection for acessada
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Definir o tipo de conteúdo como HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Tentar estabelecer a conexão com o banco de dados
            DbConnection dbConnection = new DbConnection();
            Connection conn = dbConnection.getConnection();
            
            // Verificar se a conexão é válida
            if (conn != null && conn.isValid(2)) { // Timeout de 2 segundos
                out.println("<h1>Conexão bem-sucedida!</h1>");
                out.println("<h2>Teste1234589<h2>");
                out.println("<p>O banco de dados foi conectado com sucesso.</p>");
            } else {
                out.println("<h1>Falha na Conexão</h1>");
                out.println("<p>A conexão com o banco de dados falhou.</p>");
                out.println("<h2>Teste</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Test123<h2>");
            out.println("<h1>Erro na Conexão</h1>");
            out.println("<p>Ocorreu um erro ao tentar conectar ao banco de dados: " + e.getMessage() + "</p>"); 
            e.printStackTrace();
        }
    }
}
