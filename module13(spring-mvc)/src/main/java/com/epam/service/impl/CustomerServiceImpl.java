package com.epam.service.impl;

import com.epam.dao.CustomerDAO;
import com.epam.service.CustomerService;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    @Override
    public List<String> getCustomerProducts(String customer) {
        return customerDAO.getCustomerProducts(customer);
    }

    @Override
    public List<String> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}
