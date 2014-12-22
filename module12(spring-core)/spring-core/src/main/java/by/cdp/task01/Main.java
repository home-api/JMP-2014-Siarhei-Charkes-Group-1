package by.cdp.task01;

import by.cdp.task01.service.StoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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

        StoreService storeService = (StoreService) context.getBean("storeService");

        System.out.println(storeService.getMessage());
    }

}
