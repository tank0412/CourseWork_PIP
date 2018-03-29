package mbeans;

import dao.ChatsDao;
import dao.MessagesDao;
import dao.UserDao;
import ejb.XmppManager;
import models.Chats;
import models.Messages;
import models.Users;
import org.primefaces.context.RequestContext;
import org.richfaces.event.ItemChangeEvent;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ChatsBean implements Serializable {
    private Long current;
    private List<Chats> list;
    private Chats chat;
    private List<Messages> ms;
    private String text;
    private Long chatId;
    private String chatName;
    private Long userId;

    @EJB
    XmppManager xm;

    @ManagedProperty(value = "#{authChecking.user}")
    private Users user;

    public ChatsBean(){
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public void updateCurrent(ItemChangeEvent event) {
        setCurrent(Long.parseLong(event.getNewItemName()));
        ChatsDao cd = new ChatsDao();
        chat = cd.getById(current);
        MessagesDao md = new MessagesDao();
        ms = md.getAllFrom(chat);
        RequestContext.getCurrentInstance().execute("upmsg()");
    }

    public List<Chats> getList() {
        return list;
    }

    public void setList(List<Chats> list) {
        this.list = list;
    }

    public void getUserChats(){

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Chats getChat() {
        return chat;
    }

    public void setChat(Chats chat) {
        this.chat = chat;
    }

    public List<Messages> getMs() {
        return ms;
    }

    public void setMs(List<Messages> ms) {
        this.ms = ms;
    }

    public void sendMessage()  {
        if(current == null){
            return;
        }
        ChatsDao cd = new ChatsDao();
        chat = cd.getById(current);
        cd.update(chat);
        MessagesDao md = new MessagesDao();
        Messages msg = new Messages(user, chat, text);
        Date d = new Date();
        msg.setTime(d);
        cd.update(chat);
        md.add(msg);
        chat = cd.getById(current);
        ms = md.getAllFrom(chat);

        if(chat.getId() == 3){
            UserDao ud = new UserDao();
            List<Users> lusers = ud.getAllWithJID();
            try {
                xm = new XmppManager("jabber.ru", 5222);
                xm.init();
                xm.performLogin("arck1pip", "123456arck");
                for (Users u:
                        lusers) {
                    xm.sendMessage(text, u.getJid());
                }
            }
            catch (Exception e)
            {
                showMsg("Connection error", "Something wrong, check XMPP API.");

                e.printStackTrace();
            }
            finally {
                xm.destroy();
            }
        }

        RequestContext.getCurrentInstance().execute("upmsg()");

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void createNewChat(){

        ChatsDao cd = new ChatsDao();
        UserDao ud = new UserDao();
        Chats newchat = new Chats(chatName, "private", user);
        cd.add(newchat);
        ud.addChat(user, newchat);
        RequestContext.getCurrentInstance().execute("alert('Chat created');");
    }
    public void leaveFromChat(){

        UserDao ud = new UserDao();
        ud.removeChat(user, chatId);
        RequestContext.getCurrentInstance().execute("alert('You leaved from chat');");
    }

    public void inviteUser(){
        UserDao ud = new UserDao();
        Users usr = ud.getById(userId);
        ud.addChat(usr, chat);
    }
    private void showMsg(String head, String body){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, head, body);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

}
