package by.cdp.task01;

import by.cdp.task01.service.ProductService;
import by.cdp.task01.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/22/2014
 * Time: 4:18 AM
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "src/main/resources/task01/spring-main-configuration.xml");

        Map<String, String> map = (Map<String, String>) context.getBean("map");
        System.out.println(map);

        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getMessage());

        ProductService productService = context.getBean(ProductService.class);
        System.out.println(productService.getMessage());

        userService = (UserService) context.getBean("us");
        System.out.println(userService.getMessage());
    }

}
