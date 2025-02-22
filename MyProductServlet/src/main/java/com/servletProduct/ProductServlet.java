package com.servletProduct;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/PData";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "skreddy@7379";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		String quantityStr = request.getParameter("quantity");
		String priceStr = request.getParameter("price");

		// Convert quantity and price to proper data types
		int quantity = 0;
		double price = 0.0;
		try {
			quantity = Integer.parseInt(quantityStr); // Convert quantity to integer
			price = Double.parseDouble(priceStr); // Convert price to double
		} catch (NumberFormatException e) {
			// If there's an error in parsing, return a response indicating the issue
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print("{\"status\":\"error\",\"message\":\"Invalid number format for quantity or price.\"}");
			out.close();
			return;
		}

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			PreparedStatement st = con.prepareStatement(
					"INSERT INTO ProductTable (productId, productName, category, quantity, price) VALUES (?, ?, ?, ?, ?)"
					);

			st.setString(1, productId);
			st.setString(2, productName);
			st.setString(3, category);
			st.setInt(4, quantity);  // Set quantity as an integer
			st.setDouble(5, price);  // Set price as a double

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				out.print("{\"status\":\"success\",\"message\":\"Product successfully added!\"}");
			} else {
				out.print("{\"status\":\"error\",\"message\":\"Failed to add product.\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
		} finally {
			out.close();
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchProductId = request.getParameter("searchProductId");

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ProductTable WHERE productId = ?");
			ps.setString(1, searchProductId);

			ResultSet rs = ps.executeQuery();

			StringBuilder jsonResponse = new StringBuilder();
			jsonResponse.append("[");

			boolean hasData = false;
			while (rs.next()) {
				hasData = true;
				jsonResponse.append("{")
				.append("\"productId\":\"").append(rs.getString("productId")).append("\",")
				.append("\"productName\":\"").append(rs.getString("productName")).append("\",")
				.append("\"category\":\"").append(rs.getString("category")).append("\",")
				.append("\"quantity\":").append(rs.getInt("quantity")).append(",")
				.append("\"price\":").append(rs.getDouble("price"))
				.append("},");
			}

			if (hasData) {
				jsonResponse.setLength(jsonResponse.length() - 1); // Remove trailing comma
			}
			jsonResponse.append("]");

			out.print(jsonResponse.toString());

		} catch (Exception e) {
			out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
		} finally {
			out.close();
		}
	}
}
