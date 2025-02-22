package com.jsonObject;

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

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/JsonObjectProductData")
public class JsonObjectProductData extends HttpServlet {
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

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        // Validate and parse quantity and price
        int quantity = 0;
        double price = 0.0;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Invalid number format for quantity or price.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(jsonResponse.toString());
            out.close();
            return;
        }

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(
                "INSERT INTO ProductTable (productId, productName, category, quantity, price) VALUES (?, ?, ?, ?, ?)"
            );

            st.setString(1, productId);
            st.setString(2, productName);
            st.setString(3, category);
            st.setInt(4, quantity);
            st.setDouble(5, price);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Product successfully added!");
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Failed to add product.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        out.print(jsonResponse.toString());
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchProductId = request.getParameter("searchProductId");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONArray jsonArray = new JSONArray();

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ProductTable WHERE productId = ?");
            ps.setString(1, searchProductId);

            ResultSet rs = ps.executeQuery();
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                JSONObject product = new JSONObject();
                product.put("productId", rs.getString("productId"));
                product.put("productName", rs.getString("productName"));
                product.put("category", rs.getString("category"));
                product.put("quantity", rs.getInt("quantity"));
                product.put("price", rs.getDouble("price"));

                jsonArray.put(product);
            }

            if (!hasData) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print(new JSONObject().put("status", "error").put("message", "Product not found").toString());
            } else {
                out.print(jsonArray.toString());
            }

        } catch (Exception e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(errorResponse.toString());
        } finally {
            out.close();
        }
    }
}
