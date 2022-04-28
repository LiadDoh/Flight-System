package com.FlightsSystem.Controllers;


import com.FlightsSystem.Facades.AdministratorFacade;
import com.FlightsSystem.Facades.AnonymousFacade;
import com.FlightsSystem.Models.Administrators;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Customers;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    private AnonymousFacade anonymousFacade = new AnonymousFacade();

    //get all customers
    @GetMapping("/customers")
    public List<Customers> getCustomers(Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        return admin.getAllCustomers();
    }

    //add new customer
    @PutMapping("/newCustomer")
    public void newCustomer(@RequestBody Customers customer ,Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.addCustomer(customer);
    }

    //add new airline
    @PutMapping("/newAirline")
    public void newAirline(@RequestBody AirlineCompanies airline, Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.addAirline(airline);
    }

    //add new administrator
    @PutMapping("/newAdministrator")
    public void newAdministrator(@RequestBody Administrators administrator, Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.addAdministrator(administrator);
    }

    //delete customer
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestBody Customers customer, Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.removeCustomer(customer);
    }

    //delete airline
    @DeleteMapping("/deleteAirline")
    public void deleteAirline(@RequestBody AirlineCompanies airline, Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.removeAirline(airline);
    }

    //delete administrator
    @DeleteMapping("/deleteAdministrator")
    public void deleteAdministrator(@RequestBody Administrators administrator, Authentication authentication) {
        AdministratorFacade admin = (AdministratorFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        admin.removeAdministrator(administrator);
    }

}
