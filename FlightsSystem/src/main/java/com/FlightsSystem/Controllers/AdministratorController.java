package com.FlightsSystem.Controllers;


import com.FlightsSystem.Facades.AdministratorFacade;
import com.FlightsSystem.Facades.LoginToken;
import com.FlightsSystem.Models.Administrators;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    //get all customers
    @GetMapping("/customers")
    public List<Customers> getCustomers(LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        return admin.getAllCustomers();
    }

    //add new customer
    @PutMapping("/newCustomer")
    public void newCustomer(@RequestBody Customers customer ,LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.addCustomer(customer);
    }

    //add new airline
    @PutMapping("/newAirline")
    public void newAirline(@RequestBody AirlineCompanies airline, LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.addAirline(airline);
    }

    //add new administrator
    @PutMapping("/newAdministrator")
    public void newAdministrator(@RequestBody Administrators administrator, LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.addAdministrator(administrator);
    }

    //delete customer
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestBody Customers customer, LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.removeCustomer(customer);
    }

    //delete airline
    @DeleteMapping("/deleteAirline")
    public void deleteAirline(@RequestBody AirlineCompanies airline, LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.removeAirline(airline);
    }

    //delete administrator
    @DeleteMapping("/deleteAdministrator")
    public void deleteAdministrator(@RequestBody Administrators administrator, LoginToken token) {
        AdministratorFacade admin = new AdministratorFacade(token);
        admin.removeAdministrator(administrator);
    }

}
