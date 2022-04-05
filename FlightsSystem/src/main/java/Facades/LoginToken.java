package Facades;

public class LoginToken {
    private long id;
    private String username;
    private int role;

    public LoginToken(long id, String username, int role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRole() {
        return role;
    }
}
