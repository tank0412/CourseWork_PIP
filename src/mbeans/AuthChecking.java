package mbeans;

import dao.UserDao;
import models.Users;
import org.primefaces.context.RequestContext;

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
    private Users user;

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
    public void login(){
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        if(user != null && pass.equals(user.getPassword())){
            isLogged = true;
            userId = user.getId();
            this.user = user;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "main.xhtml";
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

}
