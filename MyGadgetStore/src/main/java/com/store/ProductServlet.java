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

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gadget_store";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "skreddy@7379";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PrintWriter out = response.getWriter()) {

            // Retrieve product details from request
            String productId = request.getParameter("product_id");
            String brandName = request.getParameter("brand_name");
            String category = request.getParameter("category");
            String quantityStr = request.getParameter("quantity");

            int quantity = Integer.parseInt(quantityStr);

            // Insert product into database
            String query = "INSERT INTO products (product_id, brand_name, category, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, productId);
            ps.setString(2, brandName);
            ps.setString(3, category);
            ps.setInt(4, quantity);

            int result = ps.executeUpdate();

            // Respond to the client
            if (result > 0) {
                out.print("success");
            } else {
                out.print("failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while saving product data");
        }
    }
    
	@Override 
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String productIdParam = request.getParameter("searchProductId");

        if (productIdParam == null || productIdParam.isEmpty()) {
            response.getWriter().write("error|Product ID is required");
            return;
        }

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PrintWriter out = response.getWriter()) {

            String query = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productIdParam);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                out.write(rs.getString("product_id") + "|" +
                          rs.getString("brand_name") + "|" +
                          rs.getString("category") + "|" +
                          rs.getInt("quantity"));
            } else {
                out.write("error|No product found with this ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("error|An error occurred. Please try again later.");
        }
    }

}
