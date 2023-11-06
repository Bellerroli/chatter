package belrol.chat.chatter.mvc.model;

public class Message {
    private int id;
    private String username;
    private String message;
    private long timestamp;

    public Message(int id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Message(int id, String username, String message, long timestamp) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return this.username+":::"+this.message;
    }
}
