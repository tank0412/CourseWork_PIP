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
    public String Message = "DefaultText";

    public void sentMessage() {
        /*
        Tunnel tunnel =  Tunnel.newInstance("queue", "localhost");
         Consumer consumer = new Consumer(tunnel,"queue");
         tunnel.publish("HelloWorld");
         Message = tunnel.receive();
         tunnel.disconnect();
         tunnel.close();
         */

        Tunnel tunnel =  Tunnel.newInstance("queue", "localhost");
        Producer producer = new Producer(tunnel,"queue");
        Consumer consumer = new Consumer(tunnel,"queue");
        producer.send("HelloWorld");
        Message = consumer.receive();

       // Producer producer = new Producer("queue", "localhost");
        //producer.send("HelloWorld");
       // Consumer consumer = new Consumer("queue", "localhost");
       //Tunnel tunnel =  Tunnel.newInstance("queue", "localhost");
       // tunnel.publish("HelloWorld");
       // Message = tunnel.receive();
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String Message) {
        this.Message = Message; }

}
