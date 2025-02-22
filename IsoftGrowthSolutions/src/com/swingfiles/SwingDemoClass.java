package com.swingfiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SwingDemoClass {

	private static Map<String, Employee> employeeMap = new HashMap<>();
	private static final String FILE_NAME = "D:\\ISoft";

	public static void main(String[] args) 
	{
		loadEmployeeData();

		JFrame frame = new JFrame("Employee Data");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		Dimension textFieldDimension = new Dimension(378, 25);

		JLabel idLabel = new JLabel("Employee ID:");
		JTextField idField = new JTextField();
		idField.setPreferredSize(textFieldDimension);

		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		nameField.setPreferredSize(textFieldDimension);

		JLabel ageLabel = new JLabel("Age:");
		JTextField ageField = new JTextField();
		ageField.setPreferredSize(textFieldDimension);

		JLabel genderLabel = new JLabel("Gender:");
		JTextField genderField = new JTextField();
		genderField.setPreferredSize(textFieldDimension);

		JLabel emailLabel = new JLabel("Email:");
		JTextField emailField = new JTextField();
		emailField.setPreferredSize(textFieldDimension);

		JLabel phoneLabel = new JLabel("Phone Number:");
		JTextField phoneField = new JTextField();
		phoneField.setPreferredSize(textFieldDimension);

		JLabel stateLabel = new JLabel("State:");
		JTextField stateField = new JTextField();
		stateField.setPreferredSize(textFieldDimension);

		JLabel countryLabel = new JLabel("Country:");
		JTextField countryField = new JTextField();
		countryField.setPreferredSize(textFieldDimension);

		JLabel statusLabel = new JLabel("Active Status:");
		JTextField statusField = new JTextField();
		statusField.setPreferredSize(textFieldDimension);

		JLabel developerTypeLabel = new JLabel("Developer Type:");
		JTextField developerTypeField = new JTextField();
		developerTypeField.setPreferredSize(textFieldDimension);

		JButton submitButton = new JButton("Submit");
		JButton resetButton = new JButton("Reset");
		JButton fetchButton = new JButton("Get by ID");

		submitButton.setPreferredSize(new Dimension(100, 30));
		resetButton.setPreferredSize(new Dimension(100, 30));
		fetchButton.setPreferredSize(new Dimension(120, 30));

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);
		gbc.gridx = 1;
		panel.add(idField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(nameLabel, gbc);
		gbc.gridx = 1;
		panel.add(nameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(ageLabel, gbc);
		gbc.gridx = 1;
		panel.add(ageField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(genderLabel, gbc);
		gbc.gridx = 1;
		panel.add(genderField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(emailLabel, gbc);
		gbc.gridx = 1;
		panel.add(emailField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(phoneLabel, gbc);
		gbc.gridx = 1;
		panel.add(phoneField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(stateLabel, gbc);
		gbc.gridx = 1;
		panel.add(stateField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(countryLabel, gbc);
		gbc.gridx = 1;
		panel.add(countryField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(statusLabel, gbc);
		gbc.gridx = 1;
		panel.add(statusField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		panel.add(developerTypeLabel, gbc);
		gbc.gridx = 1;
		panel.add(developerTypeField, gbc);

		// Submit and Reset buttons aligned horizontally
		gbc.gridx = 0;
		gbc.gridy = 10;
		panel.add(submitButton, gbc);
		gbc.gridx = 1;
		panel.add(resetButton, gbc);

		// Fetch button centered below
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(fetchButton, gbc);

		frame.add(panel);
		frame.setVisible(true);

		submitButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = idField.getText();
				String name = nameField.getText();
				int age = Integer.parseInt(ageField.getText());
				String gender = genderField.getText();
				String email = emailField.getText();
				String phone = phoneField.getText();
				String state = stateField.getText();
				String country = countryField.getText();
				String status = statusField.getText();
				String developerType = developerTypeField.getText();

				Employee employee = new Employee(id, name, age, gender, email, phone, state, country, status, developerType);
				employeeMap.put(id, employee);
				saveEmployeeData();

				JOptionPane.showMessageDialog(frame, "Employee data saved successfully.");
			}
		});

		resetButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				idField.setText("");
				nameField.setText("");
				ageField.setText("");
				genderField.setText("");
				emailField.setText("");
				phoneField.setText("");
				stateField.setText("");
				countryField.setText("");
				statusField.setText("");
				developerTypeField.setText("");
			}
		});

		fetchButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = idField.getText();
				Employee employee = employeeMap.get(id);

				if (employee != null) 
				{
					nameField.setText(employee.getName());
					ageField.setText(String.valueOf(employee.getAge()));
					genderField.setText(employee.getGender());
					emailField.setText(employee.getEmail());
					phoneField.setText(employee.getPhone());
					stateField.setText(employee.getState());
					countryField.setText(employee.getCountry());
					statusField.setText(employee.getStatus());
					developerTypeField.setText(employee.getDeveloperType());
				} 
				else 
				{
					JOptionPane.showMessageDialog(frame, "Employee not found.");
				}
			}
		});
	}

	private static void saveEmployeeData() 
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) 
		{
			oos.writeObject(employeeMap);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private static void loadEmployeeData() 
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) 
		{
			employeeMap = (HashMap<String, Employee>) ois.readObject();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			// File may not exist initially
		}
	}

	static class Employee implements Serializable 
	{
		private String id;
		private String name;
		private int age;
		private String gender;
		private String email;
		private String phone;
		private String state;
		private String country;
		private String status;
		private String developerType;

		public Employee(String id, String name, int age, String gender, String email, String phone, String state, String country, String status, String developerType) 
		{
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.email = email;
			this.phone = phone;
			this.state = state;
			this.country = country;
			this.status = status;
			this.developerType = developerType;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getGender() {
			return gender;
		}

		public String getEmail() {
			return email;
		}

		public String getPhone() {
			return phone;
		}

		public String getState() {
			return state;
		}

		public String getCountry() {
			return country;
		}

		public String getStatus() {
			return status;
		}

		public String getDeveloperType() {
			return developerType;
		}
	}
}
