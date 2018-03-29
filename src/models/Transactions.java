package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transactions")//, schema = "s225130"
public class Transactions implements Serializable{
    private long id;
    private Users sender;
    private Users receiver;
    private Long coins;
    private Date date;
    private String comment;

    public Transactions() {}
    public Transactions(Users sender, Users receiver, Long coins, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.coins = coins;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coins", nullable = true)
    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactions that = (Transactions) o;

        if (id != that.id) return false;
        if (sender != that.sender) return false;
        if (receiver != that.receiver) return false;
        if (coins != null ? !coins.equals(that.coins) : that.coins != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (coins != null ? coins.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "id", nullable = false)
    public Users getSender() {
        return this.sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "receiverId", referencedColumnName = "id", nullable = false)
    public Users getReceiver() {
        return this.receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }
}
