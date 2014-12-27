package by.cdp.task01.dao;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 12/27/2014
 * Time: 8:59 AM
 */
public class UserRepositoryFactory {

    private final static UserRepository USER_REPOSITORY = new UserRepository();

    public UserRepository createUserRepository() {
        return USER_REPOSITORY;
    }

}
