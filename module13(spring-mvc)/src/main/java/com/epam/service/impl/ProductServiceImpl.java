package com.epam.service.impl;

import com.epam.dao.ProductDAO;
import com.epam.service.ProductService;

import java.util.List;

/**
 * Created by Yahor_Karabitsyn on 1/26/2015.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Override
    public List<String> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public String getProductInfo(String productName) {
        return productDAO.getProductInfo(productName);
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
