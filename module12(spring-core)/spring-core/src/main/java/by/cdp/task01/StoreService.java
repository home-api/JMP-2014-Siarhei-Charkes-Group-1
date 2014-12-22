package by.cdp.task01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/22/2014
 * Time: 4:34 AM
 */
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public String getMessage() {
        return "service, " + storeRepository.getMessage();
    }

}
