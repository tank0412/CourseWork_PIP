package mbeans;

import dao.OrdersDao;
import dao.RequestexecDao;
import dao.UserDao;
import models.Orders;
import models.Requestexec;
import models.Users;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean implements Serializable{

    private int listParam = 1;
    private List<Orders> orders;

    private Long taskId;
    private Orders task;
    private Long executorId;
    private List<Requestexec> reqlist;
    private Boolean isOwner = false;
    private String reqmsg;

    @ManagedProperty(value = "#{authChecking}")
    private AuthChecking user;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public TaskBean(){
        OrdersDao od = new OrdersDao();
        orders = od.getAll();

    }

    public void changeParam(int p){
        listParam = p;
        OrdersDao od = new OrdersDao();
        UserDao ud = new UserDao();
        Users userE = ud.getById(user.getUserId());
        switch (p){
            case 0: orders = od.getAll();
                break;
            case 1: orders = od.getOpenOrders();
                break;
            case 2: orders = od.getOpenOrders(userE);
                break;
            case 3: orders = od.getClosedByExecutor(userE);
                break;
            case 4: orders = od.getByCustomer(userE);
                break;
            default:
                break;
        }
    }

    public AuthChecking getUser() {
        return user;
    }

    public void setUser(AuthChecking user) {
        this.user = user;
    }

    public void setListParam(int listParam) {
        this.listParam = listParam;
    }

    public String getReqmsg() {
        return reqmsg;
    }

    public void setReqmsg(String reqmsg) {
        this.reqmsg = reqmsg;
    }

    public int getListParam() {
        return listParam;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;

        if(taskId != null) {
            OrdersDao od = new OrdersDao();
            task = od.getById(taskId);
            if(task.getCustomer().getId() == user.getUser().getId()){
                RequestexecDao rqd = new RequestexecDao();
                reqlist = rqd.getByTaskId(taskId);
                isOwner = true;
            }
            else
            {
                isOwner = false;
                reqlist = new ArrayList<Requestexec>();
            }
        }
    }

    public void openTask(Long taskId){
        this.taskId = taskId;
        if(taskId != null) {
            OrdersDao od = new OrdersDao();
            task = od.getById(taskId);
            if(task.getCustomer().getId() == user.getUser().getId()){
                RequestexecDao rqd = new RequestexecDao();
                reqlist = rqd.getByTaskId(taskId);
                isOwner = true;
            }
            else
            {
                reqlist = new ArrayList<Requestexec>();
                isOwner = false;
            }
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outcome = "task.xhtml";
        try {
            facesContext.getExternalContext().redirect(outcome);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Requestexec> getReqlist() {
        return reqlist;
    }

    public void setReqlist(List<Requestexec> reqlist) {
        this.reqlist = reqlist;
    }

    public Orders getTask() {
        return task;
    }

    public void setTask(Orders task) {
        this.task = task;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public void chooseExecutor(){
        UserDao ud = new UserDao();
        Users user = ud.getById(executorId);
        if(user != null){
            OrdersDao od = new OrdersDao();
            task.setExecutor(user);
            od.update(task);
        }
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }

    public void sendRequest(){
        UserDao ud = new UserDao();
        Requestexec rq = new Requestexec();
        rq.setMsg(reqmsg);
        rq.setOrderid(taskId);
        rq.setUserid(user.getUser().getId());
        RequestexecDao rd = new RequestexecDao();
        rd.add(rq);

        RequestContext.getCurrentInstance().execute("alert('Request sended!')");
    }

    public void changeStatus(){
        OrdersDao od = new OrdersDao();
        Orders order = od.getById(taskId);
        order.setOpen(!order.getOpen());
        od.update(order);
        RequestContext.getCurrentInstance().execute("alert('Status changed!')");
    }
}
