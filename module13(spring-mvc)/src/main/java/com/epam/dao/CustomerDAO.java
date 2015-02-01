package com.epam.dao;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public interface CustomerDAO {

    List<String> getCustomerProducts(String customer);

    List<String> getAllCustomers();

}
