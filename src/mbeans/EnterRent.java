package mbeans;

import dao.RentDao;
import dao.Rent_prolongDao;
import models.Rent;
import models.Rent_prolong;

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

    public void enter(long Client_ID) {
        Rent rent;
        RentDao rd = new RentDao();
        //rent = rd.getByNickname(login);
        //if(rent == null){
            rent = new Rent(Car_ID, Client_ID, Date_start_rent,Date_end_rent, Price_of_rent );
        //String result = "Submitted values: " + Car_ID + ", " + Client_ID + Date_start_rent + ", " + Date_end_rent + ", " + Price_of_rent + ", " ;
       //RequestContext.getCurrentInstance().execute("alert(result);");
            rd.add(rent);
            //user = rd.getByNickname(login);
          //  isLogged = true;
           // userId = user.getId();
          //  this.user = user;
          //  FacesContext facesContext = FacesContext.getCurrentInstance();
          //  String outcome = "main.xhtml";
          //  try {
          //      facesContext.getExternalContext().redirect(outcome);
          //  }
          //  catch (Exception e){
          //      e.printStackTrace();
          //  }
      //  }
      //  else
     //       RequestContext.getCurrentInstance().execute("alert('Registration failed');");

    }
    public void prolongate(Long Client_ID) {
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
