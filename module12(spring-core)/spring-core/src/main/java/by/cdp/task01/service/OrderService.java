package by.cdp.task01.service;

import by.cdp.task01.dao.OrderRepository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:29 AM
 */
public class OrderService {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String getMessage() {
        return "OrderService (" + orderRepository.getMessage() + ")";
    }
}
