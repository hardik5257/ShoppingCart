package shopping.cart.test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import shopping.cart.Product;
import shopping.promotion.CheapestFreeItemPromotion;

public class CheapestFreeItemPromotionTest {
	
	@Test
	void testGetCheapestProduct() {
        Product item1 = new Product("A", 10.00, 1);
        Product item2 = new Product("B", 6.00, 1);
        Product item3 = new Product("C", 4.00, 1);
        Product item4 = new Product("D", 2.00, 1);   
        Map<String, Product> products = new LinkedHashMap<String, Product>();;
        products.put("A", item1);
        products.put("B", item2);
        products.put("C", item3);
        products.put("D", item4);
        CheapestFreeItemPromotion cheapesetProduct = new CheapestFreeItemPromotion(products, 0.0);
        Product cheapestProduct = cheapesetProduct.getCheapestProduct();
        Assert.assertEquals(cheapestProduct.description(), "D");
	}
	
	@Test
	void testGetCheapestProductWithSameValueForTwoPruducts() {
        Product item1 = new Product("A", 1.00, 1);
        Product item2 = new Product("B", 1.00, 1);
        Product item3 = new Product("C", 4.00, 1);
        Product item4 = new Product("F", 2.00, 1);   
        Map<String, Product> products = new LinkedHashMap<String, Product>();;
        products.put("A", item1);
        products.put("B", item2);
        products.put("C", item3);
        products.put("F", item4);
        CheapestFreeItemPromotion cheapesetProduct = new CheapestFreeItemPromotion(products, 0.0);
        Product cheapestProduct = cheapesetProduct.getCheapestProduct();
        boolean result = false;
        if(cheapestProduct.description().equals("A") || cheapestProduct.description().equals("B")) {
        	result = true;
        }
        Assert.assertEquals(result, true);
	}
}
