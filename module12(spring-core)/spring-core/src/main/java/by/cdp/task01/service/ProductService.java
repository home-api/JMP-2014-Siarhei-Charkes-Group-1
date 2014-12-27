package by.cdp.task01.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:45 AM
 */
public class ProductService {

    private SellerService sellerService;

    @Autowired
    public ProductService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public String getMessage() {
        return sellerService.getMessage() + " -> ProductService";
    }
}
