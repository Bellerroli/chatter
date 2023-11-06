package belrol.chat.chatter.mvc.controller;

import belrol.chat.chatter.mvc.dao.MessageDao;
import belrol.chat.chatter.mvc.model.Message;

import java.util.List;

public class MessageController {
    MessageDao messageDao = new MessageDao();

    public List<Message> getMessages(){
        return messageDao.getAllMessages();
    }

    public String saveMessage(String username, String msg){
        return messageDao.insertMessage(username, msg, System.currentTimeMillis());
    }
}
