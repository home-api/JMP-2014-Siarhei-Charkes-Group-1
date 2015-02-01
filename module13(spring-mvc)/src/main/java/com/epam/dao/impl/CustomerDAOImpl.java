package com.epam.dao.impl;

import com.epam.dao.CustomerDAO;

import java.util.*;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public class CustomerDAOImpl implements CustomerDAO {

    private static final Map<String, List<String>> CUSTOMER_PRODUCTS = new HashMap<String, List<String>>();

    static {
        CUSTOMER_PRODUCTS.put("customer1", Arrays.asList("product1", "product6"));
        CUSTOMER_PRODUCTS.put("customer2", Arrays.asList("product3"));
        CUSTOMER_PRODUCTS.put("customer3", Arrays.asList("product2"));
        CUSTOMER_PRODUCTS.put("customer4", Arrays.asList("product1", "product2", "product3", "product6"));
        CUSTOMER_PRODUCTS.put("customer5", Arrays.asList("product3", "product3", "product5"));
    }

    @Override
    public List<String> getCustomerProducts(String customer) {
        return CUSTOMER_PRODUCTS.get(customer);
    }

    @Override
    public List<String> getAllCustomers() {
        List<String> customers = new ArrayList<String>();
        for (String customer : CUSTOMER_PRODUCTS.keySet()) {
            customers.add(customer);
        }
        return customers;
    }
}
