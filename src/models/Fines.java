package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fines")//, schema = "s225130"
public class Fines implements Serializable {
    private Long id;
    private Long Client_ID;
    private Date Date_get_fine;
    private String Reason_of_fine;
    private Long Fine_price;

    public Fines() {
    }

    public Fines(Long Client_ID, Date Date_get_fine, String Reason_of_fine, Long Fine_price ) {
        this.Client_ID = Client_ID;
        this.Date_get_fine = Date_get_fine;
        this.Reason_of_fine = Reason_of_fine;
        this.Fine_price = Fine_price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "сlient_id")
    public Long getClient_ID() { return Client_ID; }

    public void setClient_ID(Long Client_ID) { this.Client_ID = Client_ID; }

    @Basic
    @Column(name = "date_get_fine")
    public Date getDate_get_fine() { return Date_get_fine; }

    public void setDate_get_fine(Date Date_get_fine) { this.Date_get_fine = Date_get_fine; }

    @Basic
    @Column(name = "reason_of_fine")
    public String getReason_of_fine() { return Reason_of_fine; }

    public void setReason_of_fine(String Reason_of_fine) { this.Reason_of_fine = Reason_of_fine; }

    @Basic
    @Column(name = "fine_price")
    public Long getFine_price() { return Fine_price; }

    public void setFine_price(Long Fine_price) { this.Fine_price = Fine_price; }

}
