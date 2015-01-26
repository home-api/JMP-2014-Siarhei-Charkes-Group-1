package com.epam.service;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public interface ProductService {

    List<String> getAllProducts();

    Integer getProductPrice(String productName);

}
