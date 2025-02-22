package com.scannerfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpsList {

	public static void main(String[] args) 
	{

		List<EmpDetails> employees = new ArrayList<EmpDetails>();
		List<EmpDetails> minorEmployees = new ArrayList<>();

		// Number of employees
		Scanner scanner = new Scanner(System.in);   
		System.out.print("Enter the number of employees: ");
		int numberOfEmployees = scanner.nextInt();
		scanner.nextLine(); // the newline character enter

		// Loop to get employee details
		for (int i = 0; i < numberOfEmployees; i++) 
		{
			System.out.println("Enter details for Employee " + (i + 1) + ":");
			EmpDetails emp = new EmpDetails();

			System.out.print("Enter Employee ID: ");
			emp.setEmpId(scanner.nextInt());
			scanner.nextLine(); 

			System.out.print("Enter Employee Name: ");
			emp.setEmpName(scanner.nextLine());

			System.out.print("Enter Employee Age: ");
			emp.setAge(scanner.nextInt());
			scanner.nextLine(); 

			employees.add(emp);

			if(emp.getAge()<18) {
				minorEmployees.add(emp);
			}
		}

		// Display all employees
		System.out.println("\nYou Entered " + numberOfEmployees + " Employee Details:\n");
		for (EmpDetails emp : employees){
			System.out.println(emp);
		}
		if(!minorEmployees.isEmpty()) {
			for(EmpDetails emp : minorEmployees) {
				System.out.println("Minor Employees:");
				System.out.println(emp);
			}
		}
		else {
			System.out.println("There are no minor employees");
		}

		scanner.close();
	}

}
