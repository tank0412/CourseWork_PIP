package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rent")//, schema = "s225130"
public class Rent implements Serializable {
    private Long id;
    private Long Car_ID;
    private Long Client_ID;
    private Date Date_start_rent;
    private Date Date_end_rent;
    private Long Price_of_rent;
    private Boolean isprolongated;
    private Boolean isconfirmed;

    public Rent() {
    }

    public Rent(Long car_ID,Long client_ID, Date date_start_rent, Date date_end_rent,Long price_of_rent ) {
        this.Car_ID = car_ID;
        this.Client_ID = client_ID;
        this.Date_start_rent = date_start_rent;
        this.Date_end_rent = date_end_rent;
        this.Price_of_rent = price_of_rent;
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
    @Column(name = "Car_ID")
    public Long getCarID() {
        return Car_ID;
    }

    public void setCarID(Long Car_ID) {
        this.Car_ID = Car_ID;
    }

    @Basic
    @Column(name = "Client_ID")
    public Long getClient_ID() { return Client_ID; }

    public void setClient_ID(Long Client_ID) { this.Client_ID = Client_ID; }

    @Basic
    @Column(name = "Date_start_rent")
    public Date getDate_start_rent() { return Date_start_rent; }

    public void setDate_start_rent(Date Date_start_rent) { this.Date_start_rent = Date_start_rent; }

    @Basic
    @Column(name = "Date_end_rent")
    public Date getDate_end_rent() { return Date_end_rent; }

    public void setDate_end_rent(Date Date_end_rent) { this.Date_end_rent = Date_end_rent; }

    @Basic
    @Column(name = "Price_of_rent")
    public Long getPrice_of_rent() { return Price_of_rent; }

    public void setPrice_of_rent(Long Price_of_rent) { this.Price_of_rent = Price_of_rent; }

    @Basic
    @Column(name = "isprolongated")
    public Boolean getisprolongated() { return isprolongated; }

    public void setisprolongated(Boolean Date_end_rent) { this.isprolongated = isprolongated; }

    @Basic
    @Column(name = "isconfirmed")
    public Boolean getisconfirmed() { return isconfirmed; }

    public void setisconfirmed(Boolean isconfirmed) { this.isconfirmed = isconfirmed; }

}
