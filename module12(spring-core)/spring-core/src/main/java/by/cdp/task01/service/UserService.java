package by.cdp.task01.service;

import by.cdp.task01.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:45 AM
 */
public class UserService {

    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    private UserService(ProductService productService) {
        this.productService = productService;
    }

    public static UserService createUserService(ProductService productService) {
        return new UserService(productService);
    }

    public String getMessage() {
        return productService.getMessage() + " -> UserService (" + userRepository.getMessage() + ")";
    }
}
