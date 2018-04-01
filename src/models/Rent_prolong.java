package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Rent_prolong")//, schema = "s225130"
public class Rent_prolong implements Serializable {
    private Long id;
    private Long rent_ID;
    private Long Client_ID;
    private Date new_date_end_rent;
    private Long new_price_of_rent;
    private Boolean isconfirmed;

    public Rent_prolong() {
    }

    public Rent_prolong(Long rent_ID,Long client_ID, Date new_date_end_rent, Long new_price_of_rent) {
        this.rent_ID = rent_ID;
        this.Client_ID = client_ID;
        this.new_date_end_rent = new_date_end_rent;
        this.new_price_of_rent = new_price_of_rent;
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
    @Column(name = "rent_ID")
    public Long getrent_ID() {
        return rent_ID;
    }

    public void setrent_ID(Long rent_ID) {
        this.rent_ID = rent_ID;
    }

    @Basic
    @Column(name = "new_date_end_rent")
    public Date getnew_date_end_rent() { return new_date_end_rent; }

    public void setnew_date_end_rent(Date new_date_end_rent) { this.new_date_end_rent = new_date_end_rent; }

    @Basic
    @Column(name = "new_price_of_rent")
    public Long getnew_price_of_rent() { return new_price_of_rent; }

    public void setnew_price_of_rent(Long new_price_of_rent) { this.new_price_of_rent = new_price_of_rent; }

    @Basic
    @Column(name = "isconfirmed")
    public Boolean getisconfirmed() { return isconfirmed; }

    public void setisconfirmed(Boolean isconfirmed) { this.isconfirmed = isconfirmed; }

}
