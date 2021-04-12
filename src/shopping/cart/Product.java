package shopping.cart;

public class Product implements CartItem {

	private double itemPrice;
	private String name;
	private int count;
	
	public Product(String name, double itemPrice, int count) {
		this.name = name;
		this.itemPrice = itemPrice;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public double value() {
		return itemPrice;
	}

	@Override
	public String description() {
		return name;
	}

}
