package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "chats") //, schema = "s225130"
public class Chats implements Serializable {
    private long id;
    private String name;
    private String type;
    private Users creater;
    private Date dateBegin;

    private Collection<Messages> messages;
    private Collection<Orders> orders;
    private Collection<Users> users;

    public Chats() {
    }

    public Chats(String name, String type, Users creater) {
        this.name = name;
        this.creater = creater;
        this.type = type;
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
    @Column(name = "name", nullable = true, length = 512)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "dateBegin", nullable = true)
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chats chats = (Chats) o;

        if (id != chats.id) return false;
        if (creater != chats.creater) return false;
        if (name != null ? !name.equals(chats.name) : chats.name != null) return false;
        if (type != null ? !type.equals(chats.type) : chats.type != null) return false;
        if (dateBegin != null ? !dateBegin.equals(chats.dateBegin) : chats.dateBegin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "createrId", referencedColumnName = "id", nullable = false)
    public Users getCreater() {
        return this.creater;
    }
    public void setCreater(Users creater) {
        this.creater = creater;
    }

    @OneToMany(mappedBy = "chat")
    public Collection<Messages> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Messages> creater) {
        this.messages = creater;
    }

    @OneToMany(mappedBy = "chat")
    public Collection<Orders> getOrders() {
        return this.orders;
    }

    public void setOrders(Collection<Orders> orders) {
        this.orders = orders;
    }

    @ManyToMany(mappedBy = "chats")
    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }
}
