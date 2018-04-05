package mbeans;

//import com.rabbitmq.client.Consumer;

import rabbitmqjms.Consumer;
import rabbitmqjms.Producer;
import rabbitmqjms.Tunnel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "EnterMessage")
@SessionScoped
public class EnterMessage {
    public static String Message = "DefaultText";
    public static String Sendmsg = "HelloWorld";
    static Tunnel tunnel;
    static Producer producer;
    static Consumer consumer;
    public void sentMessage() {
        AuthChecking.initJMS();
        producer.send(Sendmsg);
        receive();
    }
    public void receive() {
        Message = consumer.receive();
        AuthChecking.destroyJMS();
        //close();
    }
    public void close() {
        tunnel.disconnect();
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String Message) {
        this.Message = Message; }

}
