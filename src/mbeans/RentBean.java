package mbeans;

import dao.RentDao;
import models.Rent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "rentBean")
@ViewScoped
public class RentBean implements Serializable {

    private Long rentId;
    private Rent rent;
    private String nickname;


    @ManagedProperty(value = "#{EnterRent.enter}")
    private Rent me;

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long userId) {
        this.rentId = userId;
        if(userId == null){
            this.rentId = me.getId();
        }
        RentDao ud = new RentDao();
        rent = ud.getById(this.rentId);
    }

    public Rent getRent() {
        if(rentId == null){
            this.rentId = me.getId();
            RentDao ud = new RentDao();
            rent = ud.getById(this.rentId);
        }
            return rent;
    }

    public void setUser(Rent rent) {
        this.rent = rent;
    }

    public Rent getMe() {
        return me;
    }

    public void setMe(Rent me) {
        this.me = me;
    }
}
