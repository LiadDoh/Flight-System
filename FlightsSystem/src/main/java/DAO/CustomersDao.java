package DAO;

import Models.Customers;
import Models.Flights;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao implements DAO<Customers> {
    private final String TABLE_NAME = "Customers";
    private List<Customers> customers = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    @Override
    public Customers get(int id) {
        Customers customer = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            customer = new Customers(
                    rs.getLong("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("phoneNo"),
                    rs.getString("creditCardNo"),
                    rs.getLong("userId")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customers> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"),
                        rs.getString("phoneNo"),
                        rs.getString("creditCardNo"),
                        rs.getLong("userId")

                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public void add(Customers customer) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (firstName, lastName, address, phoneNo, creditCardNo, userId) VALUES ('" + customer.firstName + "', '" + customer.lastName + "', '" + customer.address + "', '" + customer.phoneNo + "', '" + customer.creditCardNo + "', " + customer.userId + ")");     } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customers customer) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET firstName = '" + customer.firstName + "', lastName = '" + customer.lastName + "', address = '" + customer.address + "', phoneNo = '" + customer.phoneNo + "', creditCardNo = '" + customer.creditCardNo + "', userId = " + customer.userId + " WHERE id = " + customer.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customers customer) {
//        try {
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + countries.id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
