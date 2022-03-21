package Facades;

import Models.AirlineCompanies;
import Models.Countries;
import Models.Flights;
import Models.Users;

import java.sql.Date;
import java.util.List;

public abstract class FacadeBase {


    public List<Flights> getAllFlights() {
        return null;
    }

    public Flights getFlightById(long id) {
        return null;
    }

    public List<Flights> getFlightsByParameter(int originCountryId, int destinationCountryId,Date departureDate) {
        return null;
    }

    public List<AirlineCompanies> getAllAirlines() {
        return null;
    }

    public AirlineCompanies getAirlineById(long id) {
        return null;
    }

    public List<AirlineCompanies> getAirlinesByParameter(String name, String countryId) {
        return null;
    }

    public List<Countries> getAllCountries() {
        return null;
    }

    public Countries getCountryById(int id) {
        return null;
    }

    public void createNewUser(Users user) {

    }
}
