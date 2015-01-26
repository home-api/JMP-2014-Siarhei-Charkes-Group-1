package com.epam;

import com.epam.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yahor_Karabitsyn on 1/23/2015.
 */
@Controller
public class CustomerController {

    @RequestMapping(value = "/")
    public ModelAndView helloWorld() {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-main-configuration.xml");

        CustomerService customerService = (CustomerService) context.getBean("customerService");

        ModelAndView model = new ModelAndView("customers");
        model.addObject("allCustomers", customerService.getAllCustomers());
        return model;
    }
}
