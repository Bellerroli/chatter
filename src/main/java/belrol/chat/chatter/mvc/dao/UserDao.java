package belrol.chat.chatter.mvc.dao;


import belrol.chat.chatter.mvc.db.DatabaseConnection;
import belrol.chat.chatter.mvc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while(rs.next()){
                users.add(new User(rs.getString("username"), rs.getString("password")));
            }
            rs.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username){
        try{
            PreparedStatement stmt = DatabaseConnection.getInstance()
                    .getConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new User(rs.getString("username"), rs.getString("password"));
            }
            return null;
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String insertUser(String username, String hashedPwd){
        try{
            if(isUser(username)){
                return "User is already registered";
            }
            PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO users(username, password) VALUES(?,?)");
            stmt.setString(1, username);
            stmt.setString(2, hashedPwd);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) return "Failed to register user(db)";
            return "Registration successful";
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return "Failed to register user";
    }

    public String insertSalt(String username, byte[] salt){
        try{
            PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO salts(username, salt) VALUES(?,?)");
            stmt.setString(1, username);
            stmt.setBytes(2, salt);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) return "Failed to add salt(db)";
            return "Salt inserted";
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isUser(String username){
        try{
            PreparedStatement stmt = DatabaseConnection.getInstance()
                    .getConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            if(stmt.executeQuery().next()) return true;
            return false;
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public byte[] getSalt(String username){
        try{
            PreparedStatement stmt = DatabaseConnection.getInstance()
                    .getConnection().prepareStatement("SELECT * FROM salts WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBytes("salt");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

}
