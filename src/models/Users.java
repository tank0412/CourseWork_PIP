package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")//, schema = "s225130"
public class Users implements Serializable {
    private long id;
    private String nickname;
    private String name;
    private String description;
    private Ranks rank;
    private Date registrationDate;
    private String jid;
    /**
     * The amount available to the user
     */
    private Long coins;
    private Integer rating;
    private String password;
    private Long hash;

    private Collection<Assignment> assignments;
    /**
     * Chats in which the user is creater
     */
    private Collection<Chats> chatsCreater;
    private Collection<Messages> messages;
    private Collection<Orders> ordersCustomer;
    private Collection<Orders> ordersExecutor;
    private Collection<Transactions> transactionsSender;
    private Collection<Transactions> transactionsReceiver;
    /**
     * Chats in which the user is standing
     */
    private Collection<Chats> chats;

    public Users() {
    }

    public Users(String nickname, String name, String description, Ranks rank, Long coins, Integer rating, String pass) {
        this.name = name;
        this.nickname = nickname;
        this.description = description;
        this.rank = rank;
        this.coins = coins;
        this.rating = rating;
        this.password = pass;
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
    @Column(name = "nickname", nullable = true, length = 512)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    @Column(name = "jid", length = 256)
    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Basic
    @Column(name = "registrationDate", nullable = true)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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
    @Column(name = "rating", nullable = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "hash", nullable = true)
    public Long getHash() {
        return hash;
    }

    public void setHash(Long hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (nickname != null ? !nickname.equals(users.nickname) : users.nickname != null) return false;
        if (name != null ? !name.equals(users.name) : users.name != null) return false;
        if (description != null ? !description.equals(users.description) : users.description != null) return false;
        if (rank != null ? !rank.equals(users.rank) : users.rank != null) return false;
        if (registrationDate != null ? !registrationDate.equals(users.registrationDate) : users.registrationDate != null)
            return false;
        if (coins != null ? !coins.equals(users.coins) : users.coins != null) return false;
        if (rating != null ? !rating.equals(users.rating) : users.rating != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (hash != null ? !hash.equals(users.hash) : users.hash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (coins != null ? coins.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Collection<Assignment> receiver) {
        this.assignments = receiver;
    }

    @OneToMany(mappedBy = "creater")
    public Collection<Chats> getChatsCreater() {
        return chatsCreater;
    }

    public void setChatsCreater(Collection<Chats> chats) {
        this.chatsCreater = chats;
    }

    @OneToMany(mappedBy = "sender")
    public Collection<Messages> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Messages> messages) {
        this.messages = messages;
    }

    @OneToMany(mappedBy = "customer")
    public Collection<Orders> getOrdersCustomer() {
        return ordersCustomer;
    }

    public void setOrdersCustomer(Collection<Orders> orders) {
        this.ordersCustomer = orders;
    }

    @OneToMany(mappedBy = "executor")
    public Collection<Orders> getOrdersExecutor() {
        return ordersExecutor;
    }

    public void setOrdersExecutor(Collection<Orders> orders) {
        this.ordersExecutor = orders;
    }

    @OneToMany(mappedBy = "sender")
    public Collection<Transactions> getTransactionsSender() {
        return transactionsSender;
    }

    public void setTransactionsSender(Collection<Transactions> transactions) {
        this.transactionsSender = transactions;
    }

    @OneToMany(mappedBy = "receiver")
    public Collection<Transactions> getTransactionsReceiver() {
        return transactionsReceiver;
    }

    public void setTransactionsReceiver(Collection<Transactions> transactions) {
        this.transactionsReceiver = transactions;
    }

    @ManyToOne
    @JoinColumn(name = "rankid", referencedColumnName = "id")
    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userschats", joinColumns = {
            @JoinColumn(name = "userid", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "chatid", nullable = false, updatable = false)
    })
    public Collection<Chats> getChats() {
        return chats;
    }

    public void setChats(Collection<Chats> chats) {
        this.chats = chats;
    }
}
