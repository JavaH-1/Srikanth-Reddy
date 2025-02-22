package com.marshal;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

@xmlRootElement
public class Employee {
	
	private int empId;
	private int empName;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getEmpName() {
		return empName;
	}
	public void setEmpName(int empName) {
		this.empName = empName;
	}
	
	
}
