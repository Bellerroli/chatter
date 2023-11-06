package belrol.chat.chatter.mvc.controller;

import belrol.chat.chatter.mvc.dao.UserDao;
import belrol.chat.chatter.mvc.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class UserController {
    private UserDao userDAO;

    public UserController() {
        this.userDAO = new UserDao();
    }

    public String registerUser(String username, String pwd){
        try{
            byte[] salt = createSalt();
            String hashed = hashPwd(pwd, salt);
            String msg = userDAO.insertUser(username, hashed);
            System.out.println(userDAO.insertSalt(username, salt));
            return msg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public User login(String username, String pwd){
        User user = userDAO.getUserByUsername(username);
        if(user == null) return null;
        if(!hashPwd(pwd, userDAO.getSalt(username)).equals(user.getPwd())) return null;
        return user;
    }

    private String hashPwd(String pwd, byte[] salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
            return new String(hashedPassword, StandardCharsets.UTF_8);
        }catch(NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }
        throw new RuntimeException("Hashing password failed!");
    }

    private byte[] createSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
