package by.cdp.task01.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:30 AM
 */
public class ItemService {

    private String storeName;

    @Autowired
    private OrderService orderService;

    public ItemService(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getMessage() {
        return orderService.getMessage() + " -> ItemService, storeName = " + storeName;
    }
}
