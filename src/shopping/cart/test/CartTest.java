package shopping.cart.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import shopping.cart.Cart;
import shopping.cart.CartItem;
import shopping.cart.Product;
import shopping.promotion.Coupon;

class CartTest {

	@Test
	void testCheckoutWithOnlyOnePromotion() {
		Cart cart = new Cart();
        CartItem item1 = new Product("A", 10.00, 1);
        CartItem item2 = new Product("B", 5.25, 1);
        CartItem item3 = new Product("C", 4.00, 1);
        CartItem item4 = new Product("F", 2.00, 1);
        CartItem coupon1 = new Coupon(1, "Half price", "Buy A and get B for half price. Half price is applied to B items based on the number of A items.");        
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        cart.add(item4);
        cart.add(coupon1); 
        double total = cart.checkout();
		Assert.assertEquals(total, 18.63, 0.01);
	}
	
	@Test
	void testCheckoutWithTwoPromotions() {
		Cart cart = new Cart();
        CartItem item1 = new Product("A", 10.00, 1);
        CartItem item2 = new Product("B", 5.50, 3);
        CartItem item3 = new Product("C", 4.00, 1);
        CartItem item4 = new Product("F", 2.00, 1);
        CartItem coupon1 = new Coupon(1, "Half price", "Buy A and get B for half price. Half price is applied to B items based on the number of A items.");
        CartItem coupon2 = new Coupon(2, "Cheapest item free", "Buy any 3 items from A, B, C, D & E list of products and get the cheapest one for free.");
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        cart.add(item4);
        cart.add(coupon1);
        cart.add(coupon2);
        double total = cart.checkout();
		Assert.assertEquals(total, 28.50, 0.01);
	}
	
	@Test
	void testCheckoutWithInvalidPromotions() {
		Cart cart = new Cart();
        CartItem item1 = new Product("A", 10.00, 1);
        CartItem item2 = new Product("B", 5.25, 1);
        CartItem item3 = new Product("C", 4.00, 1);
        CartItem item4 = new Product("F", 2.00, 1);
        CartItem coupon1 = new Coupon(3, "InValid", "Invalid promotion");
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        cart.add(item4);
        cart.add(coupon1);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
        	cart.checkout();
        });
        assertEquals("Invalid Coupon Id: 3", exception.getMessage());
        /*Exception exception = assertThrows(RuntimeException.class, () -> {
        	cart.checkout();
        });
        assertEquals("Invalid Coupon Id: 3", exception.getMessage());*/
	}
	
	@Test
	void testCheckoutWithoutPromotion() {
		Cart cart = new Cart();
        CartItem item1 = new Product("A", 10.00, 1);
        CartItem item2 = new Product("B", 5.25, 1);
        CartItem item3 = new Product("C", 4.00, 1);
        CartItem item4 = new Product("F", 2.00, 1);
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        cart.add(item4);
        double total = cart.checkout();
		Assert.assertEquals(total, 21.25, 0.01);
	}
	
	@Test
	void testCheckoutWithoutNoProductTypeA() {
		Cart cart = new Cart();
        CartItem item1 = new Product("B", 5.25, 1);
        CartItem item2 = new Product("C", 4.00, 1);
        CartItem item3 = new Product("F", 2.00, 1);
        CartItem coupon1 = new Coupon(1, "Half price", "Buy A and get B for half price. Half price is applied to B items based on the number of A items.");
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        cart.add(coupon1);
        double total = cart.checkout();
		Assert.assertEquals(total, 11.25, 0.01);
	}
	
	@Test
	void testCheckoutWithNullCheck() {
		Cart cart = new Cart();
        CartItem item1 = null;
        CartItem item2 = null;
        CartItem coupon1 = null;
        cart.add(item1);
        cart.add(item2);
        cart.add(coupon1);
        double total = cart.checkout();
		Assert.assertEquals(total, 0.0, 0.01);
	}

}