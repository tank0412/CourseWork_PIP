package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "assignment") //, schema = "s225130"
public class Assignment implements Serializable{
    private long id;
    private Users user;
    private Ranks rank;
    private Date dateBegin;
    private String reason;

    public Assignment(){}
    public Assignment(Users user, Ranks rank, String reason){
        this.user = user;
        this.rank = rank;
        this.reason = reason;
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
    @Column(name = "dateBegin", nullable = true)
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Basic
    @Column(name = "reason", nullable = true, length = -1)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        if (id != that.id) return false;
        if (user != that.user) return false;
        if (rank != that.rank) return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public Users getUser() {
        return this.user;
    }

    public void setUser(Users userId) {
        this.user = userId;
    }

    @ManyToOne
    @JoinColumn(name = "rankId", referencedColumnName = "id", nullable = false)
    public Ranks getRank() {
        return this.rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }
}
