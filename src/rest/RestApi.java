package rest;

import com.google.gson.Gson;
import dao.*;
import ejb.userAuth;
import models.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
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
            RanksDao rd = new RanksDao();
            Ranks rank = rd.getByLvl(0);
            user = new Users(login, login, "", rank, 0L, 0, pass);
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
    @Path("getnews")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getNews(@QueryParam("login") String login, @QueryParam("pass") String pass) {
        Gson gson = new Gson();
        List<Messages> ms = new ArrayList<Messages>();
        if (isreg(login, pass)) {
            ChatsDao cd = new ChatsDao();
            Chats chat = cd.getById(24L);
            MessagesDao md = new MessagesDao();
            ms = md.getAllFrom(chat);
        }
        Collections.reverse(ms);
        return gson.toJson(convert(ms));
    }

    @GET
    @Path("gettasks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTasks(@QueryParam("login") String login, @QueryParam("pass") String pass,@DefaultValue("0") @QueryParam("type") int type){
        Gson gson = new Gson();
        List<Orders> ms = new ArrayList<Orders>();
        if (isreg(login, pass)) {
            OrdersDao od = new OrdersDao();
            UserDao ud = new UserDao();
            Users user = ud.getByNickname(login);
            switch (type){
                case 0: ms = od.getAll();
                    break;
                case 1: ms = od.getOpenOrders();
                    break;
                case 2: ms = od.getOpenOrders(user);
                    break;
                case 3: ms = od.getClosedByExecutor(user);
                    break;
                case 4: ms = od.getByCustomer(user);
                    break;
                default:
                    break;
            }
        }

        return gson.toJson(convertT(ms));
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
            OrdersDao od = new OrdersDao();
            UserDao ud = new UserDao();
            Users user = ud.getByNickname(login);
            ms = ud.getByFilters(nickname, id, rank);
        }
        return gson.toJson(convertP(ms));

    }

    private List<JsonMessage> convert(List<Messages> ms) {
        List<JsonMessage> msj = new ArrayList<JsonMessage>();
        for (Messages m :
                ms) {
            JsonMessage jm = new JsonMessage(m.getSender().getName(), m.getMessage(), m.getTime().toString());
            msj.add(jm);
        }
        return msj;
    }
    private List<JsonTask> convertT(List<Orders> ms) {
        List<JsonTask> msj = new ArrayList<JsonTask>();
        for (Orders o :
                ms) {
            JsonTask jm = new JsonTask(o.getId(), o.getCustomer().getNickname(), o.getDescription(),
                    o.getName(), o.getReward(), o.getLeadTime(), o.getComment());
            msj.add(jm);
        }
        return msj;
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
