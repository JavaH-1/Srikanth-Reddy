package com.studytonight.example;

public class Employee implements EmployeeInterface {
	
	int empId;
	String empName;
	String email;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void empData() {
		System.out.println("EmpId:" + empId);
		System.out.println("EmpName:" + empName);
		System.out.println("Email:" + email);
	}

}
