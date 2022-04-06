package Facades;

import DAO.AirlinesCompaniesDao;
import DAO.CountriesDao;
import DAO.FlightsDao;
import Models.AirlineCompanies;
import Models.Flights;

import java.util.List;

public class AirlineFacade extends AnonymousFacade {

    private LoginToken token;

    public AirlineFacade(LoginToken loginToken) {
        super();
    }

    //Get all flights of the airline company
    public List<Flights> getMyFlights(){
        FlightsDao flightsDao = new FlightsDao();
        return flightsDao.getFlightsByAirlineId(this.token.getId());
    }

    //Update the airline company info
    public void updateAirline(AirlineCompanies airlineCompanies) {
        if (checkInvalidAirline(airlineCompanies)) {
            throw new IllegalArgumentException("Invalid airline company");
        }
        AirlinesCompaniesDao airlinesCompaniesDao = new AirlinesCompaniesDao();
        AirlineCompanies temp = airlinesCompaniesDao.get((int) airlineCompanies.id);
        if (temp != null && temp.userId == this.token.getId())
            airlinesCompaniesDao.update(airlineCompanies);
    }


    //Add a new flight to the airline company
    public void addFlight(Flights flight) {
        if(flight.airlineCompanyId != this.token.getId())
            throw new IllegalArgumentException("Invalid airline company");
        if (!checkInvalidFlight(flight)) {
            throw new IllegalArgumentException("Invalid flight info");
        }
        FlightsDao flightsDao = new FlightsDao();
        flightsDao.add(flight);
    }

    //Update a flight of the airline company
    public void updateFlight(Flights flight) {
        if(flight.airlineCompanyId != this.token.getId())
            throw new IllegalArgumentException("The flight is not from this airline company");
        if (checkInvalidFlight(flight)) {
            throw new IllegalArgumentException("Invalid flight info");
        }
        FlightsDao flightsDao = new FlightsDao();
        if (flightsDao.get((int) flight.id) != null)
            flightsDao.update(flight);
        else
            throw new IllegalArgumentException("The flight does not exist");
    }

    //Delete a flight of the airline company
    public void removeFlight(Flights flight) {
        if(flight.airlineCompanyId != this.token.getId())
            throw new IllegalArgumentException("The flight is not from this airline company");
        FlightsDao flightsDao = new FlightsDao();
        flightsDao.delete(flight);
    }

    private boolean checkInvalidFlight(Flights flight) {
        if (flight.airlineCompanyId > 0) {
            AirlinesCompaniesDao airlineCompaniesDao = new AirlinesCompaniesDao();
            if (airlineCompaniesDao.get((int) flight.airlineCompanyId) == null) {
                return false;
            }
        }
        if (flight.departureTime == null || flight.landingTime == null) {
            return false;
        }
        if (flight.departureTime.after(flight.landingTime)) {
            return false;
        }
        if (flight.originCountryId > 0 || flight.destinationCountryId > 0) {
            if (flight.originCountryId == flight.destinationCountryId)
                return false;
            CountriesDao countriesDao = new CountriesDao();
            if (countriesDao.get(flight.originCountryId) == null) {
                return false;
            }
            if (countriesDao.get(flight.destinationCountryId) == null) {
                return false;
            }
        }
        if(flight.remainingTickets < 0)
            return false;
        return true;
    }
}
