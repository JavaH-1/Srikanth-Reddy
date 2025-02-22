package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CountryStateServlet")
public class CountryStateServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	    // Database Connection Details
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/gadget_store";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "skreddy@7379";

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String type = request.getParameter("type");

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            if ("countries".equals(type)) {
	                // Fetch all countries and return HTML options
	                String query = "SELECT * FROM countries";
	                try (PreparedStatement stmt = conn.prepareStatement(query);
	                     ResultSet rs = stmt.executeQuery()) {
	                    while (rs.next()) {
	                        out.println("<option value='" + rs.getInt("country_id") + "'>" + rs.getString("country_name") + "</option>");
	                    }
	                }
	            } else if ("states".equals(type)) {
	                // Fetch states based on selected country and return HTML options
	                int countryId = Integer.parseInt(request.getParameter("countryId"));
	                String query = "SELECT state_name FROM states WHERE country_id = ?";
	                try (PreparedStatement stmt = conn.prepareStatement(query)) {
	                    stmt.setInt(1, countryId);
	                    try (ResultSet rs = stmt.executeQuery()) {
	                        while (rs.next()) {
	                            out.println("<option value='" + rs.getString("state_name") + "'>" + rs.getString("state_name") + "</option>");
	                        }
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
}
