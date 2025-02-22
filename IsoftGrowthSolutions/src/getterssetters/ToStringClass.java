package getterssetters;

public class ToStringClass {
	
	private String productName;
	private int imeiCode;
	
	public ToStringClass(String productName,int imeiCode) {
		this.productName = productName;
		this.imeiCode = imeiCode;
	}
	
	public String toString() {
		return ("product name :" + productName +"\n"+ "imei code :" + imeiCode);
		}

	public static void main(String[] args) {
		ToStringClass tsc = new ToStringClass("parle g", 1028);
		System.out.println(tsc.toString());

	}

}
