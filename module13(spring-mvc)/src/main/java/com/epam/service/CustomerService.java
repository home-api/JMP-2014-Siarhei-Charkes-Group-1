package com.epam.service;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public interface CustomerService {

    List<String> getCustomerProducts(String customer);

    List<String> getAllCustomers();

}
