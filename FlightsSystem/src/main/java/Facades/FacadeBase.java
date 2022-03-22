package Facades;

import DAO.AirlinesCompaniesDao;
import DAO.CountriesDao;
import DAO.FlightsDao;
import DAO.UsersDao;
import Models.AirlineCompanies;
import Models.Countries;
import Models.Flights;
import Models.Users;

import java.sql.Date;
import java.util.List;

public abstract class FacadeBase {

    public List<Flights> getAllFlights() {
        List<Flights> flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.getAll();
        return flights;
    }

    public Flights getFlightById(int id) {
        Flights flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.get(id);
        return flights;
    }

    public List<Flights> getFlightsByParameter(int originCountryId, int destinationCountryId,Date departureDate) {
        List<Flights> flights = null;
        FlightsDao flightsDao = new FlightsDao();
        flights = flightsDao.getFlightsByParameters(originCountryId,destinationCountryId,departureDate);
        return flights;
    }

    public List<AirlineCompanies> getAllAirlines() {
        List<AirlineCompanies> airlines = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlines = airlinesCompaniesDao.getAll();
        return airlines;
    }

    public AirlineCompanies getAirlineById(int id) {
        AirlineCompanies airlineCompanies = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlineCompanies = airlinesCompaniesDao.get(id);
        return airlineCompanies;
    }

    public List<AirlineCompanies> getAirlinesByParameters(String name, int countryId) {
        List<AirlineCompanies> airlines = null;
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        airlines = airlinesCompaniesDao.getByParameters(name,countryId);
        return airlines;
    }

    public List<Countries> getAllCountries() {
        List<Countries> countries = null;
        CountriesDao countriesDao = new CountriesDao();
        countries = countriesDao.getAll();
        return countries;
    }

    public Countries getCountryById(int id) {
        Countries countries = null;
        CountriesDao countriesDao = new CountriesDao();
        countries = countriesDao.get(id);
        return countries;
    }

    public void createNewUser(Users user) {
        UsersDao usersDao = new UsersDao();
        usersDao.add(user);
    }
}
