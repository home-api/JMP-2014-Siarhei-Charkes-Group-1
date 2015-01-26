package com.epam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public interface ProductDAO {

    List<String> getAllProducts();

    String getProductInfo(String productName);

}
