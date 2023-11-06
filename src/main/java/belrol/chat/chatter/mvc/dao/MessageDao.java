package belrol.chat.chatter.mvc.dao;

import belrol.chat.chatter.mvc.db.DatabaseConnection;
import belrol.chat.chatter.mvc.model.Message;
import belrol.chat.chatter.mvc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public List<Message> getAllMessages(){
        List<Message> msgs = new ArrayList<>();
        try {
            Statement stmt = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, username, message, cast(extract(epoch from time) as bigint) AS timestamp FROM messages ORDER BY time");
            while(rs.next()){
                msgs.add(new Message(rs.getInt("id"), rs.getString("username"), rs.getString("message"),rs.getLong("timestamp")));
            }
            rs.close();
            return msgs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String insertMessage(String username, String msg, long timestamp){
        try{
            PreparedStatement stmt = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("INSERT INTO messages(username, message, time) VALUES(?,?,to_timestamp(?))");
            stmt.setString(1, username);
            stmt.setString(2, msg);
            stmt.setLong(3, timestamp);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) return "Failed to save message(db)";
            return "Message saved!";
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return "Failed to save message(db)";
    }
}
