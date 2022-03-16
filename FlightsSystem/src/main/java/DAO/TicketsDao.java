package DAO;

import Models.Tickets;
import Models.Users;

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

    @Override
    public Tickets get(int id) {
        Tickets ticket = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            ticket = new Tickets(
                    rs.getLong("id"),
                    rs.getLong("flightId"),
                    rs.getLong("customerId")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Tickets> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Tickets ticket = new Tickets(
                        rs.getLong("id"),
                        rs.getLong("flightId"),
                        rs.getLong("customerId")
                );
                tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public void add(Tickets ticket) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (flightId, customerId) VALUES (" + ticket.flightId + ", " + ticket.customerId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tickets ticket) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET flightId = " + ticket.flightId + ", customerId = " + ticket.customerId + " WHERE id = " + ticket.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Tickets ticket) {
//        try {
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + countries.id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
