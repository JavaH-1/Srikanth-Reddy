package com.swingfiles;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductApp {

    private static HashMap<String, Product> productDatabase = new HashMap<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Product Form");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblProductName = new JLabel("Product Name:");
        JTextField txtProductName = new JTextField();

        JLabel lblPrice = new JLabel("Price:");
        JTextField txtPrice = new JTextField();

        JLabel lblQuantity = new JLabel("Quantity:");
        JTextField txtQuantity = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnReset = new JButton("Reset");
        JButton btnGetByName = new JButton("Get By Name");

        frame.add(lblProductName);
        frame.add(txtProductName);
        frame.add(lblPrice);
        frame.add(txtPrice);
        frame.add(lblQuantity);
        frame.add(txtQuantity);
        frame.add(btnSubmit);
        frame.add(btnReset);
        frame.add(new JLabel()); // Empty space
        frame.add(btnGetByName);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = txtProductName.getText().trim();
                String priceText = txtPrice.getText().trim();
                String quantityText = txtQuantity.getText().trim();

                // Validate inputs
                if (productName.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);
                    int quantity = Integer.parseInt(quantityText);

                    Product product = new Product(productName, price, quantity);
                    productDatabase.put(productName, product);

                    // Save product details to file
                    saveProductToFile(product);

                    JOptionPane.showMessageDialog(frame, "Product details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields
                    txtProductName.setText("");
                    txtPrice.setText("");
                    txtQuantity.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Price and Quantity must be numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to save product details to file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtProductName.setText("");
                txtPrice.setText("");
                txtQuantity.setText("");
            }
        });

        btnGetByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame getByNameFrame = new JFrame("Get Product By Name");
                getByNameFrame.setSize(300, 150);
                getByNameFrame.setLayout(new GridLayout(3, 1, 10, 10));

                JLabel lblName = new JLabel("Enter Product Name:");
                JTextField txtName = new JTextField();
                JButton btnSearch = new JButton("Search");

                getByNameFrame.add(lblName);
                getByNameFrame.add(txtName);
                getByNameFrame.add(btnSearch);

                btnSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String searchName = txtName.getText().trim();
                        if (productDatabase.containsKey(searchName)) {
                            Product product = productDatabase.get(searchName);
                            JOptionPane.showMessageDialog(getByNameFrame, product.toString(), "Product Details", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(getByNameFrame, "No details found for Product Name: " + searchName, "Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                getByNameFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }

   
    private static void saveProductToFile(Product product) throws IOException {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\ISoft/Products.txt", true))) {
            writer.write(product.toString());
            writer.newLine();
            writer.newLine(); 
        }
    }
}
