package com.epam.dao.impl;

import com.epam.dao.ProductDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public class ProductDAOImpl implements ProductDAO {

    private static final Map<String, String> PRODUCT_PRICES = new HashMap<String, String>();

    static {
        PRODUCT_PRICES.put("product1", "This is product1");
        PRODUCT_PRICES.put("product2", "This is product2");
        PRODUCT_PRICES.put("product3", "This is product3");
        PRODUCT_PRICES.put("product4", "This is product4");
        PRODUCT_PRICES.put("product5", "This is product5");
    }

    @Override
    public List<String> getAllProducts() {
        List<String> products = new ArrayList<String>();
        for (String productName : PRODUCT_PRICES.keySet()) {
            products.add(productName);
        }
        return products;
    }

    @Override
    public String getProductInfo(String productName) {
        return PRODUCT_PRICES.get(productName);
    }
}
