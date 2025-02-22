package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gadget_store";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "skreddy@7379";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PrintWriter out = response.getWriter()) {

            String name = request.getParameter("customer_name");
            String id = request.getParameter("customer_id");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone_number");
            String country = request.getParameter("country");
            String state = request.getParameter("state");
            String address = request.getParameter("address");
            String pinCode = request.getParameter("pin_code");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String query = "INSERT INTO customers (customer_name, customer_id, gender, phone_number, country, state, address, pin_code, email_id, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setString(5, country);
            ps.setString(6, state);
            ps.setString(7, address);
            ps.setString(8, pinCode);
            ps.setString(9, email);
            ps.setString(10, password);

            int result = ps.executeUpdate();
            if (result > 0) {
//                out.println("<div class='alert alert-success'>Successfully registered!</div>");
            } else {
//                out.println("<div class='alert alert-danger'>Registration failed. Please try again.</div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Registration failed");
        }
    }
}
