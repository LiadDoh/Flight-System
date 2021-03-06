package com.FlightsSystem.DAO;

import com.FlightsSystem.Models.Tickets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketsDao implements DAO<Tickets> {
    private final String TABLE_NAME = "Tickets";
    private List<Tickets> tickets = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    //Get all tickets by id
    @Override
    public Tickets get(int id) {
        Tickets ticket = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            ticket = new Tickets(
                    rs.getLong("id"),
                    rs.getLong("flight_id"),
                    rs.getLong("customer_id")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return ticket;
    }

    //Get all tickets
    @Override
    public List<Tickets> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Tickets ticket = new Tickets(
                        rs.getLong("id"),
                        rs.getLong("flight_id"),
                        rs.getLong("customer_id")
                );
                tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return tickets;
    }

    //Add new ticket
    @Override
    public void add(Tickets ticket) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (flight_id, customer_id) VALUES (" + ticket.flightId + ", " + ticket.customerId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Update ticket
    @Override
    public void update(Tickets ticket) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET flight_id = " + ticket.flightId + ", customer_id = " + ticket.customerId + " WHERE id = " + ticket.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete ticket
    @Override
    public void delete(Tickets ticket) {
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + ticket.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete tickets by customer id
    public void deleteByCustomerId(long customerId) {
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE \"customer_id\" = " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete tickets by flight id
    public void deleteByFlightId(long flightId) {
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE \"flight_id\" = " + flightId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Get all tickets that belong to a customer
    public List<Tickets> getTicketsByCustomer(long customerId){
        List<Tickets> customerTickets = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM get_tickets_by_customer('" + customerId +"')");
            while (rs.next()) {
                Tickets ticket = new Tickets(
                        rs.getLong("id"),
                        rs.getLong("flight_id"),
                        rs.getLong("customer_id")
                );
                customerTickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return customerTickets;
    }
}
