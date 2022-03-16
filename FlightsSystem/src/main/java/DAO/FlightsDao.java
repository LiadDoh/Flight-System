package DAO;

import Models.Flights;
import Models.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightsDao implements DAO<Flights> {
    private final String TABLE_NAME = "Flights";
    private List<Flights> flights = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    @Override
    public Flights get(int id) {
        Flights flight = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            flight = new Flights(
                    rs.getLong("id"),
                    rs.getLong("airlineCompanyId"),
                    rs.getInt("originCountryId"),
                    rs.getInt("destinationCountryId"),
                    rs.getTimestamp("departureTime"),
                    rs.getTimestamp("landingTime"),
                    rs.getInt("remainingTickets")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List<Flights> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getLong("id"),
                        rs.getLong("airlineCompanyId"),
                        rs.getInt("originCountryId"),
                        rs.getInt("destinationCountryId"),
                        rs.getTimestamp("departureTime"),
                        rs.getTimestamp("landingTime"),
                        rs.getInt("remainingTickets")

                );
                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public void add(Flights flight) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (airlineCompanyId, originCountryId, destinationCountryId, departureTime, landingTime, remainingTickets) VALUES (" + flight.airlineCompanyId + ", " + flight.originCountryId + ", " + flight.destinationCountryId + ", '" + flight.departureTime + "', '" + flight.landingTime + "', " + flight.remainingTickets + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flights flight) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET airlineCompanyId = " + flight.airlineCompanyId + ", originCountryId = " + flight.originCountryId + ", destinationCountryId = " + flight.destinationCountryId + ", departureTime = '" + flight.departureTime + "', landingTime = '" + flight.landingTime + "', remainingTickets = " + flight.remainingTickets + " WHERE id = " + flight.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Flights flight) {
//        try {
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + countries.id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
