package com.swingfiles;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductListConsole {
   

    public static void main(String[] args) {
    	List<String> l = new ArrayList<>();
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
        frame.add(new JLabel());
        frame.add(btnGetByName);

        JFrame getByNameFrame = new JFrame("Get Product By Name");
        getByNameFrame.setSize(300, 150);
        getByNameFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblName = new JLabel("Enter Product Name:");
        JTextField txtName = new JTextField();
        JButton btnSearch = new JButton("Search");

        getByNameFrame.add(lblName);
        getByNameFrame.add(txtName);
        getByNameFrame.add(btnSearch);
        

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = lblProductName.getText().trim();
                lblProductName.setText(" ");
                
                String productPrice = lblPrice.getText();
                lblPrice.setText(" ");
                
                String productQty = lblQuantity.getText();
                lblQuantity.getText();
                
                l.add(productName + productPrice + productQty);
                txtProductName.setText("");
                txtPrice.setText("");
                txtQuantity.setText("");
                
                System.out.println(l);
                   
            }
        });
  
        frame.setVisible(true);
      
    }
}
