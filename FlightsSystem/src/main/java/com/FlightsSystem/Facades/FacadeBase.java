package com.FlightsSystem.Facades;

import com.FlightsSystem.DAO.AirlinesCompaniesDao;
import com.FlightsSystem.DAO.CountriesDao;
import com.FlightsSystem.DAO.FlightsDao;
import com.FlightsSystem.DAO.UsersDao;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Countries;
import com.FlightsSystem.Models.Flights;
import com.FlightsSystem.Models.Users;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.Date;
import java.util.List;

public abstract class FacadeBase {

    //Get all flights from database and return them as a list of flights
    public List<Flights> getAllFlights() {
        List<Flights> flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.getAll();
        return flights;
    }

    //Get one flight from database by id and return it as a flight
    public Flights getFlightById(int id) {
        if (id < 1 || id > getAllFlights().size()) {
            throw new IllegalArgumentException("Invalid flight id");
        }
        Flights flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.get(id);
        return flights;
    }


    //Get all flights from database that match the given parameters and return them as a list of flights
    public List<Flights> getFlightsByParameter(int originCountryId, int destinationCountryId, Date departureDate) {
        if (!checkInvalidFlightParameters(originCountryId, destinationCountryId, departureDate))
            throw new IllegalArgumentException("Invalid flight parameters");
        List<Flights> flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.getFlightsByParameters(originCountryId, destinationCountryId, departureDate);
        return flights;
    }


    //Get all airlines from database and return them as a list of airlines
    public List<AirlineCompanies> getAllAirlines() {
        List<AirlineCompanies> airlines = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlines = airlinesCompaniesDao.getAll();
        return airlines;
    }

    //Get one airline from database by id and return it as an airline
    public AirlineCompanies getAirlineById(int id) {
        if (id < 1 || id > getAllAirlines().size())
           throw new IllegalArgumentException("Invalid airline id");
        AirlineCompanies airlineCompanies = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlineCompanies = airlinesCompaniesDao.get(id);
        return airlineCompanies;
    }

    //Get all airlines from database that match the given parameters and return them as a list of airlines
    public List<AirlineCompanies> getAirlinesByParameters(String name, int countryId) {
        if (!checkInvalidAirlineCompaniesParameters(name, countryId))
           throw new IllegalArgumentException("Invalid airline parameters");
        List<AirlineCompanies> airlines = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlines = airlinesCompaniesDao.getByParameters(name, countryId);
        return airlines;
    }

    //Get all countries from database and return them as a list of countries
    public List<Countries> getAllCountries() {
        List<Countries> countries = null;
        CountriesDao countriesDao = new CountriesDao();
        countries = countriesDao.getAll();
        return countries;
    }

    //Get one country from database by id and return it as a country
    public Countries getCountryById(int id) {
        if (id < 1 || id > getAllCountries().size())
            throw new IllegalArgumentException("Invalid country id");
        Countries countries = null;
        CountriesDao countriesDao = new CountriesDao();
        countries = countriesDao.get(id);
        return countries;
    }

    //Create a new user in the database
    public void createNewUser(Users user) {
        checkInvalidUser(user);
        UsersDao usersDao = new UsersDao();
        usersDao.add(user);
    }


    private boolean checkInvalidAirlineCompaniesParameters(String name, int countryId) {
        if (name == null || countryId < 1 || countryId > getAllCountries().size())
            return false;
        return true;
    }


    private boolean checkInvalidFlightParameters(int originCountryId, int destinationCountryId, Date departureDate) {
        if (originCountryId < 1)
            return false;
        if (destinationCountryId < 1)
            return false;
        if (departureDate == null || departureDate.before(new Date(System.currentTimeMillis())))
            return false;
        return true;
    }

    private void checkInvalidUser(Users user) {
        if (user == null)
            throw new IllegalArgumentException("User is null");
        if (user.username == null || user.username.equals(""))
            throw new IllegalArgumentException("Username is null or empty");
        if (user.password == null || user.password.length() < 6)
           throw new IllegalArgumentException("Password is null or needs to be at least 6 characters");
        if (user.email == null || !EmailValidator.getInstance().isValid(user.email))
           throw new IllegalArgumentException("Email is null or invalid");
        if (user.user_role < 1 || user.user_role > 3)
            throw new IllegalArgumentException("Invalid user role");
    }
}
