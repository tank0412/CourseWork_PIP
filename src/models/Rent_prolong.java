package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Rent_prolong")//, schema = "s225130"
public class Rent_prolong implements Serializable {
    private Long id;
    private Long rent_id;
    private Long client_id;
    private Date new_date_end_rent;
    private Long new_price_of_rent;
    private Boolean isconfirmed;

    public Rent_prolong() {
    }

    public Rent_prolong(Long rent_id,Long client_ID, Date new_date_end_rent, Long new_price_of_rent) {
        this.rent_id = rent_id;
        this.client_id = client_ID;
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
    @Column(name = "rent_id")
    public Long getrent_id() {
        return rent_id;
    }

    public void setrent_id(Long rent_id) {
        this.rent_id = rent_id;
    }

    @Basic
    @Column(name = "client_id")
    public Long getClient_ID() {
        return client_id;
    }

    public void setClient_ID(Long client_id) {
        this.client_id = client_id;
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
