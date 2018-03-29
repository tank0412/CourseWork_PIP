package mbeans;

import dao.UserDao;
import models.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class PeopleBean implements Serializable {
    private String nickname;
    private Long id;
    private Integer rank;

    private List<Users> users;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }


    public void search(){
        UserDao ud = new UserDao();
        users = ud.getByFilters(nickname, id, rank);
    }
}
