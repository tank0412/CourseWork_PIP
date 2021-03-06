package mbeans;

import dao.UserDao;
import models.Users;
import org.primefaces.context.RequestContext;
import rabbitmqjms.Consumer;
import rabbitmqjms.Producer;
import rabbitmqjms.Tunnel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "authChecking")
@SessionScoped
public class AuthChecking {

    private Boolean isLogged = false;
    private Long userId;
    private String login;
    private String pass;
    private static Users user;
    private Long Client_ID;
    public static String nickname;

    public void checkIsAdmin() {
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        if(user != null && pass.equals(user.getPassword())) {
            userId = user.getId();
            this.user = user;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "main.xhtml";
            if (ud.isAdmin(userId) != true) {
                RequestContext.getCurrentInstance().execute("alert('You do not have rights to visit this page');");
                outcome = "main.xhtml";

                try {
                    facesContext.getExternalContext().redirect(outcome);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void checkIsLogged(){
        if(!isLogged)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "auth.xhtml";
            try {
                facesContext.getExternalContext().redirect(outcome);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
        {
            updateUser();
        }
    }
    public static void initJMS() {
        EnterMessage.tunnel = Tunnel.newInstance(user.getNickname(), "localhost");
        EnterMessage.producer =  new Producer(EnterMessage.tunnel ,user.getNickname());
        EnterMessage.consumer =  new Consumer(EnterMessage.tunnel ,user.getNickname());
    }
    public static void destroyJMS() {
        EnterMessage.tunnel.disconnect();
    }
    public void login(){
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        if(user != null && pass.equals(user.getPassword())){
            isLogged = true;
            userId = user.getId();
            this.user = user;
            AccountBean.myuser = user;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "main.xhtml";
            initJMS();
            AccountBean.sendJabberMessage("Login success");
            EnterMessage.Sendmsg = "Login success";
            nickname = user.getNickname();
            if (ud.isAdmin(userId) == true) {
                outcome = "main_admin.xhtml";
            }
            try {
                facesContext.getExternalContext().redirect(outcome);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            RequestContext.getCurrentInstance().execute("alert('authorization failed');");
    }
    public void registration(){
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        if(user == null){
            user = new Users(login, login, pass);
            ud.add(user);
            user = ud.getByNickname(login);
            isLogged = true;
            userId = user.getId();
            this.user = user;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "main.xhtml";
            try {
                facesContext.getExternalContext().redirect(outcome);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            RequestContext.getCurrentInstance().execute("alert('Registration failed');");
    }
    public void makeadmin() {
        AccountBean.sendJabberMessage("Make admin success");
        EnterMessage.Sendmsg = "Make admin success";
        UserDao ud = new UserDao();
        ud.makeAdmin(Client_ID);

    }
    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void logout(){
        isLogged = false;
        userId = null;
        pass = "";
        AccountBean.myuser = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outcome = "auth.xhtml";
        try {
            facesContext.getExternalContext().redirect(outcome);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUser(){
        UserDao ud = new UserDao();
        user = ud.getById(user.getId());
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Long getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(Long Client_ID) {
        this.Client_ID = Client_ID;
    }

}
