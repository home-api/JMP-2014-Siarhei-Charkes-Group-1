package by.cdp.task01.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 8:48 AM
 */
@Repository
public class OrderRepository {

    @Value("${initialSequence}")
    private Integer initialSequence;

    public String getMessage() {
        return "OrderRepository (initialSequence  = " + initialSequence + ")";
    }

}
