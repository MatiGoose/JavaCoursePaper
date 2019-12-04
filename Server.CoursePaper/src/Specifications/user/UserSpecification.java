package Specifications.user;

import Interfaces.Specification;
import Entities.User;

public class UserSpecification implements Specification<User> {
    private String login;
    private String pass;

    public UserSpecification(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Override
    public boolean specify(User user) {
        return (login.equals(user.getLogin())
                && pass.equals(user.getPass()));

    }
}
