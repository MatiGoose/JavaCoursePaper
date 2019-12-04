package Entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable
{
    private int userID;
    private String login;
    private String pass;
    private String userType;

    public User(int userID, String login, String pass, String userType) {
        this.userID = userID;
        this.login = login;
        this.pass = pass;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int user_id) {
        this.userID = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID &&
                Objects.equals(login, user.login) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, login, pass, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userID +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
