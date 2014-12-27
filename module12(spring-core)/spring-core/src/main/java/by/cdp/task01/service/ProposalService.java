package by.cdp.task01.service;

import by.cdp.task01.dao.UserRepository;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 7:46 AM
 */
public class ProposalService {

    private UserRepository userRepository;

    private UserService userService;

    public ProposalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private String getMessage() {
        return userService + " -> ProposalService (" + userRepository.getMessage() + ")";
    }
}
