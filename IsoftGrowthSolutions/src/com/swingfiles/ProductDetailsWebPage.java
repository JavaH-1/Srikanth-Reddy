package com.swingfiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDetailsWebPage {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Product Details Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        
        frame.setLocationRelativeTo(null);
        
        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Create labels and text fields
        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");

        // Add action listener for Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String price = priceField.getText();
                String description = descriptionField.getText();
                String quantity = quantityField.getText();

                if (name.isEmpty() || price.isEmpty() || description.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Product Details Submitted:\n" +
                            "Name: " + name + "\nPrice: " + price + "\nDescription: " + description + "\nQuantity: " + quantity);
                }
            }
        });

        // Add action listener for Reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                priceField.setText("");
                descriptionField.setText("");
                quantityField.setText("");
            }
        });

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(submitButton);
        panel.add(resetButton);

        // Add panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

