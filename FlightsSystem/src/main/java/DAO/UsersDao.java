package DAO;

import Models.Countries;
import Models.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements DAO<Users> {
    private final String TABLE_NAME = "Users";
    private List<Users> users = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    @Override
    public Users get(int id) {
        Users user = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            user = new Users(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("role"),
                    rs.getBytes("thumbnail")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Users> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Users user = new Users(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("role"),
                        rs.getBytes("thumbnail")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void add(Users user) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (username, password, email, role, thumbnail) VALUES ('" + user.username + "', '" + user.password + "', '" + user.email + "', " + user.role + ", '" + user.thumbnail + "')");        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users user) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET username = '" + user.username + "', password = '" + user.password + "', email = '" + user.email + "', role = " + user.role + ", thumbnail = '" + user.thumbnail + "' WHERE id = " + user.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Users user) {
//        try {
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + countries.id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
