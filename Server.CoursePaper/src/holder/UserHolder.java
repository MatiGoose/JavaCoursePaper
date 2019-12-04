package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.UserObserver;
import Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserHolder implements Repository<User>
{
    private static UserHolder instance;
    private ArrayList<User> userList;
    private UserObserver observer = new UserObserver();


    public static UserHolder getInstance() {
        if (instance == null) {
            instance = new UserHolder();
        }
        return instance;
    }

    private UserHolder() {
        userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public void add(User user) {
        if (userList.size() < 1) {
            user.setUserID(1);
        } else {
            user.setUserID(userList.get(userList.size() - 1).getUserID() + 1);
        }
        userList.add(user);
        observer.onAdd(user);
    }

    public void initialize(User user) {
        userList.add(user);
    }

    @Override
    public void remove(User user) {
        userList.remove(user);
        observer.onDelete(user);
    }

    @Override
    public void update(User newUser, User oldUser) {
        int userPos = userList.indexOf(oldUser);
        newUser.setUserID(oldUser.getUserID());
        userList.set(userPos, newUser);
        observer.onUpdate(newUser, oldUser);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user);
        }
        return sb.toString();
    }

    @Override
    public List<User> query(Specification<User> specification) {
        List<User> resultList = new ArrayList<>();
        for (User user : userList) {
            if (specification.specify(user)) {
                resultList.add(user);
            }
        }
        return resultList;

    }
}
