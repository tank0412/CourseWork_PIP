package rest;

import com.google.gson.Gson;
import dao.UserDao;
import ejb.userAuth;
import models.Users;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("get")
public class RestApi {

    @EJB
    userAuth auth;

    private Boolean isLogged = false;
    private Long userId;
    private String login;
    private String pass;
    private Users user;

    public RestApi(){

    }

    @GET
    @Path("teta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String teta(){
        Gson gson = new Gson();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Tets");
        list.add("is");
        list.add("God!");
        return gson.toJson(list);
    }

    private boolean isreg(String login, String pass) {
        Gson gson = new Gson();
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        return user != null && pass.equals(user.getPassword());
    }

    @GET
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean login(@QueryParam("login") String login, @QueryParam("pass") String pass) {
        Gson gson = new Gson();
        Boolean result = login != null && pass != null && isreg(login, pass);
        return result;
    }


    @GET
    @Path("reg")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String register(@QueryParam("login") String login, @QueryParam("pass") String pass) {
        Gson gson = new Gson();
        Users user;
        UserDao ud = new UserDao();
        user = ud.getByNickname(login);
        if (user == null) {
            user = new Users(login, login, pass);
            ud.add(user);
            user = ud.getByNickname(login);
            isLogged = true;
            userId = user.getId();
            auth.setUser(user);
            auth.setLogged(true);
            this.user = user;
            return gson.toJson(true);
        }
        return gson.toJson(false);
    }


    @GET
    @Path("getpeople")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getPeople(@QueryParam("login") String login, @QueryParam("pass") String pass,
                            @QueryParam("nickname") String nickname,
                            @QueryParam("id") Long id,
                            @QueryParam("ranklvl") Integer rank){
        Gson gson = new Gson();
        List<Users> ms = new ArrayList<Users>();
        if (isreg(login, pass)) {
            UserDao ud = new UserDao();
            Users user = ud.getByNickname(login);
        }
        return gson.toJson(convertP(ms));

    }


    private List<JsonPeople> convertP(List<Users> ms) {
        List<JsonPeople> msj = new ArrayList<JsonPeople>();
        for (Users p :
                ms) {
            JsonPeople jm = new JsonPeople(p.getId(), p.getNickname());
            msj.add(jm);
        }
        return msj;
    }


    public Users getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
