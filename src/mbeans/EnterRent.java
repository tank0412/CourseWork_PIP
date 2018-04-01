package mbeans;

import dao.RentDao;
import models.Rent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    public void enter() {
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
    public void prolongate() {
        RentDao rd = new RentDao();
        rd.prolongateRent(Rent_id,Date_end_rent, Price_of_rent);
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
