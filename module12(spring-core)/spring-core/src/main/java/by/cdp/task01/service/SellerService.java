package by.cdp.task01.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:30 AM
 */
public class SellerService {

    @Autowired
    private ItemService itemService;

    public String getMessage() {
         return itemService.getMessage() + " -> SellerService";
    }

}
