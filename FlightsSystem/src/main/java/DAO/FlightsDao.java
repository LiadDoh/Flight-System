package DAO;

import Models.Flights;
import Models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightsDao implements DAO<Flights> {
    private final String TABLE_NAME = "Flights";
    private List<Flights> flights = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    //Get flight by id
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
        repository.closeConnection();
        return flight;
    }

    //Get all flights
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
        repository.closeConnection();
        return flights;
    }

    //Add flight
    @Override
    public void add(Flights flight) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (airline_company_id, origin_country_id, destination_country_id, departure_time, landing_time, remaining_tickets) VALUES (" + flight.airlineCompanyId + ", " + flight.originCountryId + ", " + flight.destinationCountryId + ", '" + flight.departureTime + "', '" + flight.landingTime + "', " + flight.remainingTickets + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Update flight
    @Override
    public void update(Flights flight) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET airline_company_id = " + flight.airlineCompanyId + ", origin_country_id = " + flight.originCountryId + ", destination_country_id = " + flight.destinationCountryId + ", departure_time = '" + flight.departureTime + "', landing_time = '" + flight.landingTime + "', remaining_tickets = " + flight.remainingTickets + " WHERE id = " + flight.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete flight and all its tickets
    @Override
    public void delete(Flights flight) {
        try {
            TicketsDao ticketsDao = new TicketsDao();
            ticketsDao.deleteByFlightId(flight.id);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + flight.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete flights by country id and all its tickets
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
        repository.closeConnection();
    }

    //Delete flights by airline company id and all its tickets
    public void deleteByAirlineCompanyId(long airlineCompanyId) {
        try {
            List<Flights> flights = getFlightsByAirlineId(airlineCompanyId);
            TicketsDao ticketsDao = new TicketsDao();
            for(Flights flight : flights )
                ticketsDao.deleteByFlightId(flight.id);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE airline_company_id = " + airlineCompanyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Get flights by country id
    public List<Flights> getByCountryId(int countryId){
        List<Flights> flightsByCountryId = new ArrayList<>();
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
                flightsByCountryId.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByCountryId;
    }

    //Get flights by airline company id
    public List<Flights> getFlightsByAirlineId(long airlineCompanyId){
        List<Flights> flightsByCompanyId = new ArrayList<>();

        try {
            rs = statement.executeQuery("SELECT * FROM get_flights_by_airline_id('"+airlineCompanyId+"')");
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
                flightsByCompanyId.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByCompanyId;
    }

    //Get flights by departure time
    public List<Flights> getFlightByDepartureDate(Date departureTime){
        List<Flights> flightsByDepartureDate = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE DATE(departure_time) = " + departureTime);
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
                flightsByDepartureDate.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByDepartureDate;
    }

    //Get flights by landing time
    public List<Flights> getFlightByLandingDate(Date landingTime){
        List<Flights> flightsByLandingTime = new ArrayList<>();

        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE DATE(landing_time) = " + landingTime);
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
                flightsByLandingTime.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByLandingTime;
    }

    //Get flights by origin country id
    public List<Flights> getFlightsByOriginCountryId(int originCountryId){
        List<Flights> flightsByOriginCountryId = new ArrayList<>();

        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE origin_country_id = " + originCountryId);
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
                flightsByOriginCountryId.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByOriginCountryId;
    }

    //Get flights by destination country id
    public List<Flights> getFlightsByDestinationCountryId(int destinationCountryId){
        List<Flights> flightsByDestinationCountryId = new ArrayList<>();

        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE destination_country_id = " + destinationCountryId);
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
                flightsByDestinationCountryId.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByDestinationCountryId;
    }

    //Get flights by parameters
    public List<Flights> getFlightsByParameters(int originCountryId, int destinationCountryId, Date departureTime){
        List<Flights> flightsByParameters = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM get_flights_by_parameters('" + originCountryId + "', '" + destinationCountryId + "', '" + departureTime + "')");
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
                flightsByParameters.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByParameters;
    }

    //Get all flights that will land in a given country in the next 12 hours
    public List<Flights> getArrivalFlights(int destinationCountryId){
        List<Flights> flightsByArrivalTime = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM get_arrival_flights('" + destinationCountryId +"')");
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
                flightsByArrivalTime.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByArrivalTime;
    }

    //Get all flights that will depart from a given country in the next 12 hours
    public List<Flights> getDepartureFlights(int originCountryId){
        List<Flights> flightsByArrivalTime = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM get_departure_flights('" + originCountryId +"')");
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
                flightsByArrivalTime.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return flightsByArrivalTime;
    }

}
