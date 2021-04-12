package shopping.promotion;

import java.util.LinkedHashMap;
import java.util.Map;

import shopping.cart.Product;

public class HalfPricePromotion implements Promotion {
	private Map<String, Product> products;
    private double total;
    
    public HalfPricePromotion(Map<String, Product> products, double total) {
        this.products = new LinkedHashMap<String, Product>(products);
        this.total = total;
    }

    @Override
    public double applyPromotion() {
    	if(products.isEmpty() || products != null) {
    		if(products.containsKey("B") && products.containsKey("A")) {
        		int productBCount = products.get("B").getCount();
        		int productACount = products.get("A").getCount();
        		if(productACount >= 1 && productBCount >= 1) {
        			for(int i=0; i<productACount; i++) {
                		total = total - (products.get("B").value() / 2);
                	}
        		}
        	}
    	}
        return total;
    }
}
