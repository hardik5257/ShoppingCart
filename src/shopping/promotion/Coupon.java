package shopping.promotion;

import shopping.cart.CartItem;

public class Coupon implements CartItem {
	private String description;
	private String type;
	private int id;
	
	public Coupon(int id, String type, String description) {
		this.id = id;
		this.description = description;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String description() {
		return description;
	}
	
}
