package mbeans;

import dao.ChatsDao;
import dao.MessagesDao;
import models.Chats;
import models.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ManagedBean
@ViewScoped
public class MainBean implements Serializable {
    List<Messages> list;

    public MainBean(){
        MessagesDao md = new MessagesDao();
        ChatsDao cd = new ChatsDao();
        Chats chat = cd.getById(3);
        list = md.getAllFrom(chat);
        Collections.reverse(list);
    }

    public List<Messages> getList() {
        return list;
    }

    public void setList(List<Messages> list) {
        this.list = list;
    }
}
