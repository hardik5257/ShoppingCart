package shopping.promotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import shopping.cart.Product;

public class CheapestFreeItemPromotion implements Promotion {
	private Map<String, Product> products;
    private double total;
    private List<String> promotionProductList;
    
    public CheapestFreeItemPromotion(Map<String, Product> products, double total) {
    	this.products = new LinkedHashMap<String, Product>(products);
        this.total = total;
        promotionProductList = new ArrayList<String>(Arrays.asList("A","B","C","D","E"));
    }
    
    public Product getCheapestProduct() {
    	double cheapestValue = Double.MAX_VALUE;
    	Product cheapestProduct = null;
    	for(String product: products.keySet()) {
    		if(promotionProductList.contains(product) && products.get(product).value() < cheapestValue) {
    			cheapestProduct = products.get(product);
    			cheapestValue = products.get(product).value();
    		}
    	}
    	return cheapestProduct;
    }

    @Override
    public double applyPromotion() {
    	int count = 0;
    	for(String product: products.keySet()) {
    		if(promotionProductList.contains(product)) {
        		count ++;
        		if(count >= 3) {
        			Product cheapestProduct = getCheapestProduct();
        			total = total - cheapestProduct.value();
        			break;
        		}
        	}
    	}
        return total;
    }
}
