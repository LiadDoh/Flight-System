package com.FlightsSystem.Controllers;


import com.FlightsSystem.Facades.AirlineFacade;
import com.FlightsSystem.Facades.AnonymousFacade;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Flights;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlinesController {

    private AnonymousFacade anonymousFacade = new AnonymousFacade();
    //update airline company
    @PutMapping("/")
    public void update(@RequestBody AirlineCompanies airlineCompanies, Authentication authentication) {
        AirlineFacade airlineFacade = (AirlineFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        airlineFacade.updateAirline(airlineCompanies);
    }

    //get all airline flights
    @GetMapping("/")
    public List<Flights> get(Authentication authentication) {
        AirlineFacade airlineFacade = (AirlineFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        return airlineFacade.getMyFlights();
    }

    //add new flight to airline
    @PostMapping("/")
    public void addFlight(@RequestBody Flights flights, Authentication authentication) {
        AirlineFacade airlineFacade = (AirlineFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        airlineFacade.addFlight(flights);
    }

    //update flight from airline
    @PostMapping("/updateFlight")
    public void updateFlight(@RequestBody Flights flights, Authentication authentication) {
        AirlineFacade airlineFacade = (AirlineFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        airlineFacade.updateFlight(flights);
    }

    //delete flight from airline
    @DeleteMapping("/")
    public void deleteFlight(@RequestBody Flights flights, Authentication authentication) {
        AirlineFacade airlineFacade = (AirlineFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        airlineFacade.removeFlight(flights);
    }
}
