package mbeans;

import dao.UserDao;
import models.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    private Long userId;
    private Users user;
    private String nickname;

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
}
