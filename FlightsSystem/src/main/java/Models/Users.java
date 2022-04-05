package Models;

import java.util.Arrays;

public class Users implements POCO {

    public long id;
    public String username;
    public String password;
    public String email;
    public int user_role;
    public byte[] thumbnail;

    public Users(long id, String username, String password, String email, int user_role, byte[] thumbnail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_role = user_role;
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", user_role='" + user_role + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
