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

    //Get customer by id
    @Override
    public Customers get(int id) {
        Customers customer = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            customer = new Customers(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("phone_no"),
                    rs.getString("credit_card_no"),
                    rs.getLong("user_id")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    //Get all customers
    @Override
    public List<Customers> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone_no"),
                        rs.getString("credit_card_no"),
                        rs.getLong("user_id")

                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    //Create customer
    @Override
    public void add(Customers customer) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (first_name, last_name, address, phone_no, credit_card_no, user_id) VALUES ('" + customer.firstName + "', '" + customer.lastName + "', '" + customer.address + "', '" + customer.phoneNo + "', '" + customer.creditCardNo + "', " + customer.userId + ")");     } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Update customer
    @Override
    public void update(Customers customer) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET first_name = '" + customer.firstName + "', last_name = '" + customer.lastName + "', address = '" + customer.address + "', phone_no = '" + customer.phoneNo + "', credit_card_no = '" + customer.creditCardNo + "', user_id = " + customer.userId + " WHERE id = " + customer.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete customer
    @Override
    public void delete(Customers customer) {
        try {
            TicketsDao ticketsDao = new TicketsDao();
            ticketsDao.deleteByCustomerId(customer.id);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + customer.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete customer by user id
    public void deleteByUserId(Customers customer) {
        try {
            TicketsDao ticketsDao = new TicketsDao();
            ticketsDao.deleteByCustomerId(customer.userId);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE user_id = " + customer.userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get customer by user id
    public Customers getByUserId(long userId) {
        Customers customer = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE user_id = " + userId);
            while (rs.next()) {
                customer = new Customers(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone_no"),
                        rs.getString("credit_card_no"),
                        rs.getLong("user_id")

                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    //Get customer by username
    public Customers getCustomerByUsername(String username) {
        Customers customer = null;
        try {
            rs = statement.executeQuery("SELECT * FROM get_customer_by_username('" +username+ "')");
            while (rs.next()) {
                customer = new Customers(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone_no"),
                        rs.getString("credit_card_no"),
                        rs.getLong("user_id")

                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}
