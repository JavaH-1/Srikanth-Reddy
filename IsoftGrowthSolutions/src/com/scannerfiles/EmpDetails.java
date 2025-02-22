package com.scannerfiles;

public class EmpDetails {
	private int empId;
	private String empName;
	private int age;

	// Getters and Setters
	public int getEmpId() 
	{
		return empId;
	}

	public void setEmpId(int empId) 
	{
		this.empId = empId;
	}

	public String getEmpName() 
	{
		return empName;
	}

	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}

	// Overriding toString() method to display employee details
	@Override
	public String toString() 
	{
		return "Employee ID: " + empId + "\n"
				+ "Employee Name: " + empName + "\n"
				+ "Employee Age: " + age + "\n";
	}

}
