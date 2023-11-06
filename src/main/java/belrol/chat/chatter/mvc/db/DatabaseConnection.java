package belrol.chat.chatter.mvc.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private Connection connection;

    private DatabaseConnection() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.err.println("Class not found");
        }

        Properties params = new Properties();
        params.setProperty("user", "postgres");
        params.setProperty("password", "9620");
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chat", params);
            System.out.println(connection.toString());
        }catch(SQLException exception){
            System.err.println(exception.getMessage());
        }

    }

    public static DatabaseConnection getInstance() {
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
