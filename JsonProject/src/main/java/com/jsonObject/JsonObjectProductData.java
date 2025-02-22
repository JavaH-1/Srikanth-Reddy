package com.jsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/JsonObjectProductData")
public class JsonObjectProductData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/PData";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "skreddy@7379";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Parse JSON from request body
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonRequest = new JSONObject(sb.toString());

            // Extract values
            String productId = jsonRequest.getString("productId");
            String productName = jsonRequest.getString("productName");
            String category = jsonRequest.getString("category");
            int quantity = jsonRequest.getInt("quantity");
            double price = jsonRequest.getDouble("price");

            // JDBC connection to add data to the database
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String insertQuery = "INSERT INTO ProductTable (productId, productName, category, quantity, price) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, productId);
                ps.setString(2, productName);
                ps.setString(3, category);
                ps.setInt(4, quantity);
                ps.setDouble(5, price);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Product successfully added to the database!");
                    System.out.println("JSON Response: " + jsonResponse.toString());
                    out.print(jsonResponse.toString());
                } else {
                    throw new Exception("Failed to insert the product into the database.");
                }
            } catch (Exception e) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Database error: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print(errorResponse.toString());
            }

        } catch (Exception e) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Invalid input data: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(jsonResponse.toString());
        } finally {
            out.close();
        }
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
