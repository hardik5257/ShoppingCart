package shopping.cart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import shopping.promotion.CheapestFreeItemPromotion;
import shopping.promotion.Coupon;
import shopping.promotion.HalfPricePromotion;
import shopping.promotion.Promotion;

public class Cart {
	
	List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void add(CartItem cartItem) {
        cartItemList.add(cartItem);
    }
    
    public Promotion getPromotionStrategy(Map<String, Product> products, int id, double total) {
        if(id == 2) {
            return new CheapestFreeItemPromotion(products, total);
        } else if(id == 1) {
            return new HalfPricePromotion(products, total);
        } else {
        	throw new RuntimeException("Invalid Coupon Id: " + id);
        }
    }

	/*
	 * Assuming only one promotion can be applied, For example if 2 promotions where
	 * used, The final cart price will be calculated based on whichever total amount
	 * is less after applying each promotion.
	 */    
    public double checkout() {
        Map<String, Product> productMap = new LinkedHashMap<>();
        double totalBeforePromotion = 0.0;
        TreeSet<Double> cartTotal = new TreeSet<Double>();
        if(!cartItemList.isEmpty()) {
        	for(CartItem cartItem: cartItemList) {
                if(cartItem instanceof Product) {
                	Product p = (Product) cartItem;
                    productMap.put(p.description(), p);
                    totalBeforePromotion += p.value() * p.getCount();
                } else if (cartItem instanceof Coupon) {
                	cartTotal.add(getPromotionStrategy(productMap, ((Coupon) cartItem).getId(), totalBeforePromotion).applyPromotion()); 
                }
            }
        }
        return cartTotal.isEmpty() ? totalBeforePromotion : Math.min(totalBeforePromotion, cartTotal.first());
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        CartItem item1 = new Product("A", 10.00, 2);
        CartItem item2 = new Product("B", 5.00, 3);
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
        System.out.println(cart.checkout());
    }
}
