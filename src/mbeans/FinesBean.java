package mbeans;

import dao.FinesDao;
import models.Fines;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "FineBean")
@ViewScoped
public class FinesBean implements Serializable {

    private Long fineId;
    private Fines fines;
    private String nickname;


    @ManagedProperty(value = "#{EnterRent.enter}")
    private Fines me;

    public Long getRentId() {
        return fineId;
    }

    public void setRentId(Long userId) {
        this.fineId = userId;
        if(userId == null){
            this.fineId = me.getId();
        }
        FinesDao ud = new FinesDao();
        fines = ud.getById(this.fineId);
    }

    public Fines getRent() {
        if(fineId == null){
            this.fineId = me.getId();
            FinesDao ud = new FinesDao();
            fines = ud.getById(this.fineId);
        }
            return fines;
    }

    public void setUser(Fines fines) {
        this.fines = fines;
    }

    public Fines getMe() {
        return me;
    }

    public void setMe(Fines me) {
        this.me = me;
    }
}
