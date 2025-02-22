package getterssetters;

public class Product {
	
	private String productName;
	private int mrp;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMrp() {
		return mrp;
	}

	public void setMrp(int mrp) {
		this.mrp = mrp;
	}

	public static void main(String[] args) {
	Product product = new Product();
	product.setProductName("Mobile");
	product.setMrp(50000);
	System.out.println("Product Name :" + product.getProductName());
	System.out.println("Mrp :" + product.getMrp());

	}

}
