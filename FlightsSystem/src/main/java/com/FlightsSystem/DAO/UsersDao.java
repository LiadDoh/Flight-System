package com.FlightsSystem.DAO;

import com.FlightsSystem.Models.Customers;
import com.FlightsSystem.Models.Users;

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

    // Get user by id
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
                    rs.getInt("user_role"),
                    rs.getBytes("thumbnail")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return user;
    }

    // Get all users
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
                        rs.getInt("user_role"),
                        rs.getBytes("thumbnail")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return users;
    }

    // Add user
    @Override
    public void add(Users user) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (username, password, email, user_role, thumbnail) VALUES ('" + user.username + "', '" + user.password + "', '" + user.email + "', " + user.user_role + ", '" + user.thumbnail + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    // Update user
    @Override
    public void update(Users user) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET username = '" + user.username + "', password = '" + user.password + "', email = '" + user.email + "', user_role = " + user.user_role + ", thumbnail = '" + user.thumbnail + "' WHERE id = " + user.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    // Delete user
    @Override
    public void delete(Users user) {
        try {
            switch (user.user_role) {
                case 1:
                    CustomersDao customersDao = new CustomersDao();
                    Customers customer = customersDao.getByUserId(user.id);
                    customersDao.deleteByUserId(customer);
                    break;
                case 2:
                    AdministratorsDao administratorsDao = new AdministratorsDao();
                    administratorsDao.deleteByUserId(user.id);
                    break;
                case 3:
                    AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
                    airlinesCompaniesDao.deleteByUserId(user.id);
                    break;
            }
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + user.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    // Get user by username
    public Users getByUsername(String username) {
        Users user = null;
        try {
            rs = statement.executeQuery("SELECT * FROM get_user_by_username('" +username+ "')");
            rs.next();
            user = new Users(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("user_role"),
                    rs.getBytes("thumbnail")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return user;
    }
}
