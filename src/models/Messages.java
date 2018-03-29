package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages") //, schema = "s225130"
public class Messages implements Serializable{
    private long id;
    private Users sender;
    private Chats chat;
    private Date time;
    private Boolean received;
    private String message;

    public Messages(){}
    public Messages(Users sender, Chats chat, String message){
        this.sender = sender;
        this.chat = chat;
        this.message = message;
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
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "received", nullable = true)
    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Messages messages = (Messages) o;

        if (id != messages.id) return false;
        if (sender != messages.sender) return false;
        if (chat != messages.chat) return false;
        if (time != null ? !time.equals(messages.time) : messages.time != null) return false;
        if (received != null ? !received.equals(messages.received) : messages.received != null) return false;
        if (message != null ? !message.equals(messages.message) : messages.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "id", nullable = false)
    public Users getSender() {
        return this.sender;
    }

    public void setSender(Users senderId) {
        this.sender = senderId;
    }

    @ManyToOne
    @JoinColumn(name = "chatId", referencedColumnName = "id", nullable = false)
    public Chats getChat() {
        return this.chat;
    }

    public void setChat(Chats chatId) {
        this.chat = chatId;
    }
}
