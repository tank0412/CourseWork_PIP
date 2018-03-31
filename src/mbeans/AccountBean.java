package mbeans;

import dao.UserDao;
import ejb.XmppManager;
import models.Users;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ManagedBean(name = "accountBean")
@ViewScoped
public class AccountBean implements Serializable {

    @ManagedProperty(value = "#{authChecking.user}")
    private Users user;
    private String newpass;
    private String newpass2;
    private String oldpass;

    @EJB
    XmppManager xm;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass2() {
        return newpass2;
    }

    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }

    private boolean testJid(String id){
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
        Matcher m = p.matcher(id);
        return m.matches();
    }
    public void saveChanges(){

        UserDao ud = new UserDao();
        Users tmp = ud.getById(user.getId());
        if(!tmp.getNickname().equals(user.getNickname())){
            Users ch = ud.getByNickname(user.getNickname());
            if(ch != null){
                showMsg("Saving changes failed!", "User with this nickname already exists...");
                return;
            }
        }
        if(user.getJid().length() > 0 && !testJid(user.getJid())){
            showMsg("Saving changes failed!", "JID not valid!");
            return;
        }
        ud.update(user);
        showMsg("Success", "Changes saved!");
    }

    public void changePass(){
        if(!oldpass.equals(user.getPassword())){
            showMsg("Password changing error!", "Wrong old password!");
            return;
        }
        if(!newpass.equals(newpass2)){
            showMsg("Password changing error!", "Check new password!");
            return;
        }
        UserDao ud = new UserDao();
        user.setPassword(newpass);
        ud.update(user);
        showMsg("Success", "Password changed.");
    }

    public void testXMPPMessage()  {
        if(!testJid(user.getJid())){
            showMsg("Testing error", "Something wrong, check JID.");
            return;
        }
        try {
            xm = new XmppManager("jabber.ru", 5222);
            xm.init();
            xm.performLogin("tank0412", "pip0412");
            xm.sendMessage("This is test message for user №" + Long.toString(user.getId()), user.getJid());
        }
        catch (Exception e)
        {
            showMsg("Testing error", "Something wrong, check JID.");

            e.printStackTrace();
            return;
        }
        finally {
            xm.destroy();
        }
        showMsg("Testing success.", "Check " + user.getJid());
    }

    public XmppManager getXm() {
        return xm;
    }

    public void setXm(XmppManager xm) {
        this.xm = xm;
    }
    private void showMsg(String head, String body){
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, head, body);
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        RequestContext.getCurrentInstance().execute("alert('"+head + ": " + body +"');");
    }
}
