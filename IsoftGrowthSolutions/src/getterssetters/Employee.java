package getterssetters;

public class Employee{
	private String empName;
	private int empId;
	

	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setEmpName("Spiderman");
		emp.setEmpId(101);
		System.out.println("Employee Name: "+emp.getEmpName());
		System.out.println("Employee Id: "+emp.getEmpId());
	}

}
