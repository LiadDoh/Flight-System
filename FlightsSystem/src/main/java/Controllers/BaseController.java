package Controllers;


import Facades.AnonymousFacade;
import Facades.FacadeBase;
import Models.AirlineCompanies;
import Models.Countries;
import Models.Flights;
import Models.Users;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class BaseController {

    AnonymousFacade anonymous = new AnonymousFacade();
    @GetMapping("/flights")
    public List<Flights> getAllFlights() {
        return anonymous.getAllFlights();
    }

    @GetMapping("/flights/{id}")
    public Flights getFlightById(@PathVariable("id") int id) {
        return anonymous.getFlightById(id);
    }

    @GetMapping("/flights/{originId}/{destinationId}/{date}")
    public List<Flights> getFlightsByOriginDestinationAndDate(@PathVariable("originId") int originId, @PathVariable("destinationId") int destinationId, @PathVariable("date") Date date) {
        return anonymous.getFlightsByParameter(originId, destinationId, date);
    }

    @GetMapping("/airlinesCompanies")
    public List<AirlineCompanies> getAllAirlinesCompanies() {
        return anonymous.getAllAirlines();
    }

    @GetMapping("/airlinesCompanies/{id}")
    public AirlineCompanies getAirlineCompanyById(@PathVariable("id") int id) {
        return anonymous.getAirlineById(id);
    }

    @GetMapping("/countries")
    public List<Countries> getAllCountries() {
        return anonymous.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public Countries getCountryById(@PathVariable("id") int id) {
        return anonymous.getCountryById(id);
    }

    @PutMapping("/createUser")
    public void createUser(@RequestBody Users user) {
        anonymous.createNewUser(user);
    }
}
