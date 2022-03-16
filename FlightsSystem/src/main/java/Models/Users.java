package Models;

import java.util.Arrays;

public class Users implements POCO {

    public long id;
    public String username;
    public String password;
    public String email;
    public int role;
    public byte[] thumbnail;

    public Users(long id, String username, String password, String email, int role, byte[] thumbnail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
