package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gadget_store";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "skreddy@7379";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        response.setContentType("text/plain");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM customers WHERE email_id = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();
            if (rs.next()) {
                out.print("success");
            } else {
                out.print("failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Login failed");
        }
    }
}
