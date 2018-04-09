package mbeans;

import dao.RentDao;
import dao.Rent_prolongDao;
import dao.UserDao;
import models.Rent;
import models.Rent_prolong;
import models.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean(name = "EnterRent")
@SessionScoped
public class EnterRent {

    //private Boolean isLogged = false;
    private Long id;
    private Long Car_ID;
    private Long Client_ID;
    private Long Rent_id;
    private Date Date_start_rent;
    private Date Date_end_rent;
    private Long Price_of_rent;
    public ArrayList<Rent> allrent;
    public ArrayList<Rent_prolong> allRent_prolong;

    public void enter(long Client_ID) {
        AccountBean.sendJabberMessage("Rent enter success");
        EnterMessage.Sendmsg = "Rent enter success";
        Rent rent;
        RentDao rd = new RentDao();
        //rent = rd.getByNickname(login);
        //if(rent == null){
            rent = new Rent(Car_ID, Client_ID, Date_start_rent,Date_end_rent, Price_of_rent );
        //String result = "Submitted values: " + Car_ID + ", " + Client_ID + Date_start_rent + ", " + Date_end_rent + ", " + Price_of_rent + ", " ;
       //RequestContext.getCurrentInstance().execute("alert(result);");
            rd.add(rent);
    }
    public void prolongate(Long Client_ID) {
        AccountBean.sendJabberMessage("Rent prolong success");
        EnterMessage.Sendmsg = "Rent prolong success";
        Rent_prolong rent;
        Rent_prolongDao rd = new Rent_prolongDao();
        //public Rent_prolong(Long rent_ID,Long client_ID, Date new_date_end_rent, Long new_price_of_rent) {
        rent = new Rent_prolong(Rent_id, Client_ID, Date_end_rent, Price_of_rent );
        rd.add(rent);
        RentDao rd2 = new RentDao();
         rd2.prolongateRent2(Rent_id);

    }
    public void getrent() {
        RentDao fd = new RentDao();
        allrent = fd.getAll();

    }
    public void getrentbyID(long Client_ID) {
        RentDao rd = new RentDao();
        allrent = rd.getByclientid(Client_ID);

    }
    public void getRent_prolong() {
        Rent_prolongDao fd = new Rent_prolongDao();
        allRent_prolong = fd.getAll();

    }
    public void getRent_prolongbyID(long Client_ID) {
        Rent_prolongDao fd = new Rent_prolongDao();
        allRent_prolong = fd.getByclientid(Client_ID);

    }
    public void confirmrentByID() {
        Users backupusers = AccountBean.myuser;
        RentDao rd = new RentDao();
        Rent rent = rd.getById(Rent_id);
        UserDao ud = new UserDao();
        AccountBean.myuser = ud.getById(rent.getClient_ID());
        AccountBean.sendJabberMessage("Admin confirm your rent with id" + Rent_id);
        AccountBean.myuser = backupusers;
        AccountBean.sendJabberMessage("Rent confirm success");
        EnterMessage.Sendmsg = "Rent confirm success";
        rd.confirmRent(Rent_id);

    }
    public void declinerentByID() {
        Users backupusers = AccountBean.myuser;
        RentDao rd = new RentDao();
        Rent rent = rd.getById(Rent_id);
        UserDao ud = new UserDao();
        AccountBean.myuser = ud.getById(rent.getClient_ID());
        AccountBean.sendJabberMessage("Admin declined your rent with id" + Rent_id);
        AccountBean.myuser = backupusers;
        AccountBean.sendJabberMessage("Rent decline success");
        EnterMessage.Sendmsg = "Rent decline success";
        rd.declineRent(Rent_id);

    }
    public void confirmRent_prolonByID() {
        Users backupusers = AccountBean.myuser;
        Rent_prolongDao rd = new Rent_prolongDao();
        Rent_prolong rent = rd.getById(Rent_id);
        UserDao ud = new UserDao();
        AccountBean.myuser = ud.getById(rent.getClient_ID());
        AccountBean.sendJabberMessage("Admin confirm your rent prolong with id" + Rent_id);
        AccountBean.myuser = backupusers;
        AccountBean.sendJabberMessage("Rent prolong confirm success");
        EnterMessage.Sendmsg = "Rent prolong confirm success";
        rd.confirmRent(Rent_id);

    }
    public void declineRent_prolonByID() {
        Users backupusers = AccountBean.myuser;
        Rent_prolongDao rd = new Rent_prolongDao();
        Rent_prolong rent = rd.getById(Rent_id);
        UserDao ud = new UserDao();
        AccountBean.myuser = ud.getById(rent.getClient_ID());
        AccountBean.sendJabberMessage("Admin declined your rent prolong with id" + Rent_id );
        AccountBean.myuser = backupusers;
        AccountBean.sendJabberMessage("Rent prolong decline success");
        EnterMessage.Sendmsg = "Rent prolong decline success";
        rd.declineRent(Rent_id);

    }
    public Long getCar_ID() {
        return Car_ID;
    }

    public Long getClient_ID() {
        return Client_ID;
    }
    public Long getRent_id() {
        return Rent_id;
    }
    public Date getDate_start_rent() {
        return Date_start_rent;
    }
    public Date getDate_end_rent() {
        return Date_end_rent;
    }
    public Long getPrice_of_rent() {
        return Price_of_rent;
    }
    public ArrayList<Rent> getallrent() {
        return allrent;
    }
    public ArrayList<Rent_prolong> getallRent_prolong() {
        return allRent_prolong;
    }

    public void setCar_ID(Long Car_ID) {
        this.Car_ID = Car_ID;
    }

    public void setClient_ID(Long Client_ID) {
        this.Client_ID = Client_ID;
    }

    public void setRent_id(Long Rent_id) {
        this.Rent_id = Rent_id;
    }

    public void setDate_start_rent(Date Date_start_rent) {
        this.Date_start_rent = Date_start_rent;
    }
    public void setDate_end_rent(Date Date_end_rent) {
        this.Date_end_rent = Date_end_rent;
    }
    public void setPrice_of_rent(Long Price_of_rent) {
        this.Price_of_rent = Price_of_rent;
    }
    public void setallrent(ArrayList<Rent>  allrent) {
        this.allrent = allrent;
    }
    public void setaallRent_prolong(ArrayList<Rent_prolong>  allrent) {
        this.allRent_prolong = allRent_prolong;
    }



    // public Long getUserId() {
      //  return userId;
   // }


   // public Users getUser() {
        //return user;
   // }

    //public void setUser(Users user) {
     //   this.user = user;
  //  }

}
