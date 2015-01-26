package com.epam;

import com.epam.service.CustomerService;
import com.epam.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yahor_Karabitsyn on 1/23/2015.
 */
@Controller
public class CustomerController {

    private CustomerService customerService;
    private ProductService productService;

    public CustomerController() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-main-configuration.xml");
        customerService = (CustomerService) context.getBean("customerService");
        productService = (ProductService) context.getBean("productService");
    }

    @RequestMapping(value = "/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("title", "Main page");
        return model;
    }

    @RequestMapping(value = "/allCustomers")
    public ModelAndView allCustomers() {
        ModelAndView model = new ModelAndView("customers");
        model.addObject("title", "Customer service");
        model.addObject("allCustomers", customerService.getAllCustomers());
        return model;
    }

    @RequestMapping(value = "/customerProducts", method = RequestMethod.GET)
    public ModelAndView showCustomerInfo(@RequestParam("customerName") String customerName) {
        ModelAndView model = new ModelAndView("customerProducts");
        model.addObject("title", "Customer products");
        model.addObject("customerProducts", customerService.getCustomerProducts(customerName));
        return model;
    }

    @RequestMapping(value = "/allProducts")
    public ModelAndView allProducts() {
        ModelAndView model = new ModelAndView("products");
        model.addObject("title", "Product service");
        model.addObject("allProducts", productService.getAllProducts());
        return model;
    }

    @RequestMapping(value = "/productInfo", method = RequestMethod.GET)
    public ModelAndView showProductInfo(@RequestParam("productName") String productName) {
        ModelAndView model = new ModelAndView("productInfo");
        model.addObject("title", "Product info");
        model.addObject("productInfo", productService.getProductInfo(productName));
        return model;
    }
}
