package mbeans;

import dao.UserDao;
import models.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    private Long userId;
    private Users user;
    private String nickname;
    public ArrayList<Users> allusers;

    @ManagedProperty(value = "#{authChecking.user}")
    private Users me;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        if(userId == null){
            this.userId = me.getId();
        }
        UserDao ud = new UserDao();
        user = ud.getById(this.userId);
    }

    public Users getUser() {
        if(userId == null){
            this.userId = me.getId();
            UserDao ud = new UserDao();
            user = ud.getById(this.userId);
        }
            return user;
    }
    public void getusers() {
        UserDao ud = new UserDao();
        allusers = ud.getAll();

    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getMe() {
        return me;
    }

    public void setMe(Users me) {
        this.me = me;
    }

    public String getNickname() {
        nickname = user.getNickname();
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<Users> getallusers() {
        return allusers;
    }

    public void setallusers(ArrayList<Users> allusers) {
        this.allusers = allusers;
    }
}
