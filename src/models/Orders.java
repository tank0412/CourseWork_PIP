package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders") //, schema = "s225130"
public class Orders implements Serializable{
    private long id;
    private Users customer;
    private Users executor;
    private Date placementDate;
    private String description;
    private Integer leadTime;
    private Long reward;
    private Boolean open;
    private Chats chat;
    private String comment;
    private String name;

    public Orders() {}
    public Orders(Users customer, Users executor, String description, Integer leadTime, Long reward, Chats chat){
        this.customer = customer;
        this.executor = executor;
        this.description = description;
        this.leadTime = leadTime;
        this.reward = reward;
        this.chat = chat;
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
    @Column(name = "placementDate", nullable = true)
    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
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
    @Column(name = "leadTime", nullable = true)
    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    @Basic
    @Column(name = "reward", nullable = true)
    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    @Basic
    @Column(name = "open", nullable = true)
    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (customer != orders.customer) return false;
        if (executor != orders.executor) return false;
        if (placementDate != null ? !placementDate.equals(orders.placementDate) : orders.placementDate != null)
            return false;
        if (description != null ? !description.equals(orders.description) : orders.description != null) return false;
        if (leadTime != null ? !leadTime.equals(orders.leadTime) : orders.leadTime != null) return false;
        if (reward != null ? !reward.equals(orders.reward) : orders.reward != null) return false;
        if (open != null ? !open.equals(orders.open) : orders.open != null) return false;
        if (chat != null ? !chat.equals(orders.chat) : orders.chat != null) return false;
        if (comment != null ? !comment.equals(orders.comment) : orders.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (executor != null ? executor.hashCode() : 0);
        result = 31 * result + (placementDate != null ? placementDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (leadTime != null ? leadTime.hashCode() : 0);
        result = 31 * result + (reward != null ? reward.hashCode() : 0);
        result = 31 * result + (open != null ? open.hashCode() : 0);
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    public Users getCustomer() {
        return this.customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "executorId", referencedColumnName = "id", nullable = false)
    public Users getExecutor() {
        return this.executor;
    }

    public void setExecutor(Users executor) {
        this.executor = executor;
    }

    @ManyToOne
    @JoinColumn(name = "chatId", referencedColumnName = "id")
    public Chats getChat() {
        return this.chat;
    }

    public void setChat(Chats chat) {
        this.chat = chat;
    }
}
