package com.FlightsSystem.Controllers;


import com.FlightsSystem.Facades.AirlineFacade;
import com.FlightsSystem.Facades.LoginToken;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Flights;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlinesController {

    //update airline company
    @PutMapping("/")
    public void update(@RequestBody AirlineCompanies airlineCompanies, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.updateAirline(airlineCompanies);
    }

    //get all airline flights
    @GetMapping("/")
    public List<Flights> get(LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        return airlineFacade.getMyFlights();
    }

    //add new flight to airline
    @PostMapping("/")
    public void addFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.addFlight(flights);
    }

    //update flight from airline
    @PostMapping("/updateFlight")
    public void updateFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.updateFlight(flights);
    }

    //delete flight from airline
    @DeleteMapping("/")
    public void deleteFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.removeFlight(flights);
    }
}
