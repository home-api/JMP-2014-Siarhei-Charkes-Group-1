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

    private static final Map<String, Integer> PRODUCT_PRICES = new HashMap<String, Integer>();

    static {
        PRODUCT_PRICES.put("product1", 11);
        PRODUCT_PRICES.put("product2", 22);
        PRODUCT_PRICES.put("product3", 33);
        PRODUCT_PRICES.put("product4", 44);
        PRODUCT_PRICES.put("product5", 55);
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
    public Integer getProductPrice(String productName) {
        return PRODUCT_PRICES.get(productName);
    }
}
