package mbeans;

import dao.FinesDao;
import models.Fines;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean(name = "EnterFines")
@SessionScoped
public class EnterFines {

    //private Boolean isLogged = false;
    private Long id;
    private Long Client_ID;
    private Date Date_get_fine;
    private String Reason_of_fine;
    private Long Fine_price;
    public ArrayList<Fines> allfines;

    public void enter() {
        Fines fines;
        FinesDao fd = new FinesDao();
        fines = new Fines( Client_ID, Date_get_fine, Reason_of_fine,  Fine_price );
        //fines = fd.getByNickname(login);
        //if(fines == null){
        //Fines(Long Client_ID, Date Date_get_fine, String Reason_of_fine, Long Fine_price);
        fd.add(fines);
        //EnterMessage.Sendmsg = "Fine enter success";
    }
    public void getfine() {
        FinesDao fd = new FinesDao();
        allfines = fd.getAll();

    }
    public void getfinebyID(long Client_ID) {
        FinesDao fd = new FinesDao();
        allfines = fd.getByclientid(Client_ID);

    }

    public Long getClient_ID() {
        return Client_ID;
    }
    public Date getDate_get_fine() {
        return Date_get_fine;
    }
    public String getReason_of_fine() {
        return Reason_of_fine;
    }
    public Long getFine_price() {
        return Fine_price;
    }
    public ArrayList<Fines> getallfines() {
        return allfines;
    }


    public void setClient_ID(Long Client_ID) {
        this.Client_ID = Client_ID;
    }

    public void setDate_get_fine(Date Date_start_rent2) {
        this.Date_get_fine = Date_start_rent2;
    }
    public void setReason_of_fine(String Reason_of_fine) {
        this.Reason_of_fine = Reason_of_fine;
    }
    public void setFine_price(Long Fine_price) {
        this.Fine_price = Fine_price;
    }
    public void setallfines(ArrayList<Fines>  allfines) {
        this.allfines = allfines;
    }
}
