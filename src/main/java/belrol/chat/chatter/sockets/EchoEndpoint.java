package belrol.chat.chatter.sockets;

import belrol.chat.chatter.mvc.controller.MessageController;
import belrol.chat.chatter.mvc.model.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;

@ServerEndpoint("/echo")
public class EchoEndpoint {
    MessageController messageController = new MessageController();
    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            System.out.println(messageController.saveMessage(msg.split(":::")[0], msg.split(":::")[1]));
            for (Session sess: session.getOpenSessions()) {
                System.out.println(sess.getId());
                sess.getBasicRemote().sendText(msg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        try{
            List<Message> msgs = messageController.getMessages();
            for (Message msg:msgs) {
                session.getBasicRemote().sendText(msg.toString());
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

}
