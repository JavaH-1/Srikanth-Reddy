package com.swingfiles;

public class Employee 
{

	private String employeeID;
	private String employeeName;
	private String dateOfBirth;
	private String city;
	private String emailID;
	private String mobileNumber;

	public Employee(String employeeID, String employeeName, String dateOfBirth, String city, String emailID, String mobileNumber) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.dateOfBirth = dateOfBirth;
		this.city = city;
		this.emailID = emailID;
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Employee ID: " + employeeID + "\n" +
				"Name: " + employeeName + "\n" +
				"Date of Birth: " + dateOfBirth + "\n" +
				"City: " + city + "\n" +
				"Email ID: " + emailID + "\n" +
				"Mobile Number: " + mobileNumber;
	}

}
