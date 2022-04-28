package com.FlightsSystem.Controllers;


import com.FlightsSystem.Facades.AnonymousFacade;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Countries;
import com.FlightsSystem.Models.Flights;
import com.FlightsSystem.Models.Users;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class BaseController {

    AnonymousFacade anonymous = new AnonymousFacade();

    //get all flights
    @GetMapping("/flights")
    public List<Flights> getAllFlights() {
        return anonymous.getAllFlights();
    }

    //fet flight by id
    @GetMapping("/flights/{id}")
    public Flights getFlightById(@PathVariable("id") int id) {
        return anonymous.getFlightById(id);
    }

    //get flight by origin, destination and date
    @GetMapping("/flights/{originId}/{destinationId}/{date}")
    public List<Flights> getFlightsByOriginDestinationAndDate(@PathVariable("originId") int originId, @PathVariable("destinationId") int destinationId, @PathVariable("date") Date date) {
        return anonymous.getFlightsByParameter(originId, destinationId, date);
    }

    //get all airline companies
    @GetMapping("/airlinesCompanies")
    public List<AirlineCompanies> getAllAirlinesCompanies() {
        return anonymous.getAllAirlines();
    }

    //get airline company by id
    @GetMapping("/airlinesCompanies/{id}")
    public AirlineCompanies getAirlineCompanyById(@PathVariable("id") int id) {
        return anonymous.getAirlineById(id);
    }

    //get all countries
    @GetMapping("/countries")
    public List<Countries> getAllCountries() {
        return anonymous.getAllCountries();
    }

    //get country by id
    @GetMapping("/countries/{id}")
    public Countries getCountryById(@PathVariable("id") int id) {
        return anonymous.getCountryById(id);
    }

    //create new user
    @PutMapping("/createUser")
    public void createUser(@RequestBody Users user) {
        anonymous.createNewUser(user);
    }
}
