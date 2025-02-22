package getterssetters;

public class EmployeeClassConstructor {

	private String empName;
	private int empId;
		
	public EmployeeClassConstructor(String empName, int empId){
		this.empName=empName;
		this.empId=empId;
	}
	public static void main(String[] args) {
		EmployeeClassConstructor construct = new EmployeeClassConstructor("Ragnar Lothbrok", 111);
		System.out.println("Employee Name is "+construct.empName);
		System.out.println("Employee id is "+construct.empId);
	}

}
