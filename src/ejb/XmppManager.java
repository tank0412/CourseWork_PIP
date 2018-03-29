package ejb;


import lombok.Data;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import javax.ejb.Stateless;
import java.io.Serializable;


@Data
@Stateless
public class XmppManager implements Serializable {

    private static final int packetReplyTimeout = 500; // millis

    private String server;
    private int port;

    private ConnectionConfiguration config;
    private XMPPConnection connection;

    private ChatManager chatManager;
    private MessageListener messageListener;

    public XmppManager(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public XmppManager() {
    }

    public void init() throws XMPPException {

        SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);

        config = new ConnectionConfiguration(server, port, server);
        SASLAuthentication.supportSASLMechanism("PLAIN");


        connection = new XMPPConnection(config);
        connection.connect();


        chatManager = connection.getChatManager();
        messageListener = new MyMessageListener();

    }

    public void performLogin(String username, String password) throws XMPPException {
        if (connection!=null && connection.isConnected()) {
            connection.login(username, password);
        }
    }

    public void setStatus(boolean available, String status) {

        Presence.Type type = available? Presence.Type.available: Presence.Type.unavailable;
        Presence presence = new Presence(type);

        presence.setStatus(status);
        connection.sendPacket(presence);

    }

    public void destroy() {
        if (connection!=null && connection.isConnected()) {
            connection.disconnect();
        }
    }

    public void sendMessage(String message, String buddyJID) throws XMPPException {
        Chat chat = chatManager.createChat(buddyJID, messageListener);
        chat.sendMessage(message);
    }

    public void createEntry(String user, String name) throws Exception {
        Roster roster = connection.getRoster();
        roster.createEntry(user, name, null);
    }

    class MyMessageListener implements MessageListener {

        @Override
        public void processMessage(Chat chat, Message message) {
            String from = message.getFrom();
            String body = message.getBody();
        }
    }

}