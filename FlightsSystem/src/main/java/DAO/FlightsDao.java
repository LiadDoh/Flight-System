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
                    rs.getLong("airline_company_id"),
                    rs.getInt("origin_country_id"),
                    rs.getInt("destination_country_id"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("landing_time"),
                    rs.getInt("remaining_tickets")

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
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")

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
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (airline_company_id, origin_country_id, destination_country_id, departure_time, landing_time, remaining_tickets) VALUES (" + flight.airlineCompanyId + ", " + flight.originCountryId + ", " + flight.destinationCountryId + ", '" + flight.departureTime + "', '" + flight.landingTime + "', " + flight.remainingTickets + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flights flight) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET airline_company_id = " + flight.airlineCompanyId + ", origin_country_id = " + flight.originCountryId + ", destination_country_id = " + flight.destinationCountryId + ", departure_time = '" + flight.departureTime + "', landing_time = '" + flight.landingTime + "', remaining_tickets = " + flight.remainingTickets + " WHERE id = " + flight.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Flights flight) {
        try {
            TicketsDao ticketsDao = new TicketsDao();
            ticketsDao.deleteByFlightId(flight.id);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + flight.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByCountryId(int countryId) {
        try {
            List<Flights> flights = getByCountryId(countryId);
            TicketsDao ticketsDao = new TicketsDao();
            for(Flights flight : flights )
                ticketsDao.deleteByFlightId(flight.id);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE origin_country_id = " + countryId + " OR destination_country_id = " + countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByAirlineCompanyId(long airlineCompanyId) {
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE airline_company_id = " + airlineCompanyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Flights> getByCountryId(int countryId){
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE origin_country_id = " + countryId + " OR destination_country_id = " + countryId);
            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")

                );
                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }


}
