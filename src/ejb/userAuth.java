package ejb;


import models.Users;

import javax.ejb.Stateful;
import java.io.Serializable;

@Stateful
public class userAuth implements Serializable {
    private Boolean isLogged = false;
    private Long userId;
    private String login;
    private String pass;
    private Users user;

    public userAuth(){

    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
