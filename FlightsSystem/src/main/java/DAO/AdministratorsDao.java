package DAO;

import Models.Administrators;
import Models.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorsDao implements DAO<Administrators>{

    private final String TABLE_NAME = "Administrators";
    private List<Administrators> administrators = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    //Get administrator by user id
    @Override
    public Administrators get(int id) {
        Administrators administrator = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            administrator = new Administrators(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getLong("user_id")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return administrator;
    }
    //Get all administrators
    @Override
    public List<Administrators> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Administrators administrator = new Administrators(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("user_id")
                );
                administrators.add(administrator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return administrators;
    }

    //Add administrator
    @Override
    public void add(Administrators administrators) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (first_name, last_name, user_id) VALUES ('" + administrators.firstName + "', '" + administrators.lastName + "', " + administrators.userId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Update administrator
    @Override
    public void update(Administrators administrators) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET first_name = '" + administrators.firstName + "', last_name = '" + administrators.lastName + "', user_id = " + administrators.userId + " WHERE id = " + administrators.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete administrator
    @Override
    public void delete(Administrators administrators) {
        try {
            UsersDao usersDao = new UsersDao();
            Users user = usersDao.get((int)administrators.userId);
            usersDao.delete(user);
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE \"id\" = " + administrators.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get administrator by user id
    public void deleteByUserId(long userId){
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE user_id = " + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
