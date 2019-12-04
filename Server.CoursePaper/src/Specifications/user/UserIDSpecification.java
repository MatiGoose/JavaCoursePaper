package Specifications.user;

import Interfaces.Specification;
import Entities.User;

public class UserIDSpecification implements Specification<User> {
    private int userID;

    public UserIDSpecification(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean specify(User user) {
        return userID==user.getUserID();
    }
}
