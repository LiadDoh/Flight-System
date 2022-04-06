package Controllers;


import Facades.AirlineFacade;
import Facades.LoginToken;
import Models.AirlineCompanies;
import Models.Flights;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlinesController {

    @PutMapping("/update")
    public void update(@RequestBody AirlineCompanies airlineCompanies, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.updateAirline(airlineCompanies);
    }

    @GetMapping("/get")
    public List<Flights> get(LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        return airlineFacade.getMyFlights();
    }

    @PostMapping("/addFlight")
    public void addFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.addFlight(flights);
    }

    @PostMapping("/updateFlight")
    public void updateFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.updateFlight(flights);
    }

    @DeleteMapping("/deleteFlight")
    public void deleteFlight(@RequestBody Flights flights, LoginToken loginToken) {
        AirlineFacade airlineFacade = new AirlineFacade(loginToken);
        airlineFacade.removeFlight(flights);
    }
}
