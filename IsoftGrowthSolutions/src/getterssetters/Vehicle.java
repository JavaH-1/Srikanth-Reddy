package getterssetters;

public class Vehicle {
	
	private String vehicleModel;
	private int yearOfRelease;
	
	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public static void main(String[] args) {
		Vehicle veh = new Vehicle();
		veh.setVehicleModel("Vitara Brezza");
		veh.setYearOfRelease(2019);
		System.out.println(veh.getVehicleModel());
		System.out.println(veh.getYearOfRelease());
	}

}
