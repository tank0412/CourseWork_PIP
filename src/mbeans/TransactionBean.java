package mbeans;

import dao.TransactionDao;
import dao.UserDao;
import models.Transactions;
import models.Users;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name ="transactionBean")
@ViewScoped
public class TransactionBean implements Serializable {

    private List<Transactions> trs;
    private String message;
    private Long reciverId;
    private Long count;
    private Long commission = 0L;
    private Long countB;
    private Long countR;

    @ManagedProperty(value = "#{authChecking}")
    private AuthChecking auth;

    public TransactionBean(){
    }

    public void getTrList(){
        TransactionDao td = new TransactionDao();
        Users user = auth.getUser();
        trs = td.getWith(user, 5);
    }

    public Long getCount() {
        return count;
    }

    public Long getReciverId() {
        return reciverId;
    }

    public void setCount(Long count) {
        this.count = count;
        if(this.count < 500)
            this.commission = 50L;
    }

    public void setReciverId(Long reciverId) {
        this.reciverId = reciverId;
    }


    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public AuthChecking getAuth() {
        return auth;
    }

    public void setAuth(AuthChecking auth) {
        this.auth = auth;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCountB() {
        return countB;
    }

    public void setCountB(Long countB) {
        this.countB = countB;
    }

    public Long getCountR() {
        return countR;
    }

    public void setCountR(Long countR) {
        this.countR = countR;
    }

    public List<Transactions> getTrs() {
        return trs;
    }

    public void setTrs(List<Transactions> trs) {
        this.trs = trs;
    }

    public void sendCoins(){
        UserDao ud = new UserDao();
        TransactionDao td = new TransactionDao();

        Users reciver = ud.getById(reciverId);
        Users user = ud.getById(auth.getUserId());
        if(reciver == null || user == null)
        {
            RequestContext.getCurrentInstance().execute("alert('User is not found.');");
            return;
        }

        if(user.getCoins() < count + commission){
            RequestContext.getCurrentInstance().execute("alert('Not enough coins for Transaction');");
            return;
        }
        boolean flag = td.sendCoins(user, reciver, count, message);
        if(flag){
            user.setCoins(user.getCoins() - commission);
            ud.update(user);
            auth.setUser(user);
            auth.updateUser();
            RequestContext.getCurrentInstance().execute("alert('Transaction success!');");
        }
        else
        {
            RequestContext.getCurrentInstance().execute("alert('Transaction failed!');");
        }


    }
    public void buyCoins(){
        TransactionDao td = new TransactionDao();
        boolean flag =  td.buyCoins(auth.getUser(), countB);
        if(flag){
            auth.updateUser();
            if(countB >  (int)(Math.pow(2.0, auth.getUser().getRank().getLevel() * 7)) && auth.getUser().getRank().getLevel() < 9){
                UserDao ud = new UserDao();
                ud.updateLvl(auth.getUser(), Math.min(auth.getUser().getRank().getLevel(), 9));
            }
            RequestContext.getCurrentInstance().execute("alert('Transaction success!');");
        }
        else{
            RequestContext.getCurrentInstance().execute("alert('Transaction failed!');");
        }
    }

    public void receiveCoins(){
        TransactionDao td = new TransactionDao();
        Users user = auth.getUser();
        UserDao ud = new UserDao();
        if(user.getCoins() < countR + 50L){
            RequestContext.getCurrentInstance().execute("alert('Not enough coins for Transaction');");
            return;
        }
        boolean flag =  td.receiveCoins(auth.getUser(), countR);

        if(flag){
            user.setCoins(user.getCoins() - 50);
            ud.update(user);
            auth.setUser(user);
            auth.updateUser();
            RequestContext.getCurrentInstance().execute("alert('Transaction success!');");
        }
        else{
            RequestContext.getCurrentInstance().execute("alert('Transaction failed!');");
        }
    }
}
