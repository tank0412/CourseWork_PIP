package mbeans;

import dao.ChatsDao;
import dao.OrdersDao;
import dao.UserDao;
import models.Chats;
import models.Orders;
import models.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;


@ManagedBean(name = "taskCreateBean")
@SessionScoped
public class TaskCreateBean implements Serializable{
    private String name;
    private String discription;
    private Integer leadTime;
    private Long reward;
    private String comment;

    @ManagedProperty(value = "#{authChecking}")
    private AuthChecking user;

    public void createOrder(){
        UserDao ud = new UserDao();
        OrdersDao od = new OrdersDao();
        Users userEntity = ud.getById(user.getUserId());
        Chats chat = new Chats("Chat for task: " + name, "private", userEntity);
        ChatsDao cd = new ChatsDao();
        cd.add(chat);
        ud.addChat(userEntity, chat);
        Orders order = new Orders(userEntity, userEntity, discription, leadTime, reward, chat);
        order.setName(name);
        order.setComment(comment);
        order.setOpen(true);
        Date d = new Date();
        order.setPlacementDate(d);
        od.add(order);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outcome = "tasks.xhtml";
        try {
            facesContext.getExternalContext().redirect(outcome);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getReward() {
        return reward;
    }

    public String getComment() {
        return comment;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public AuthChecking getUser() {
        return user;
    }

    public void setUser(AuthChecking user) {
        this.user = user;
    }
}
