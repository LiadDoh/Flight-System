package com.FlightsSystem.Controllers;

import com.FlightsSystem.Facades.AnonymousFacade;
import com.FlightsSystem.Facades.CustomerFacade;
import com.FlightsSystem.Models.Customers;
import com.FlightsSystem.Models.Tickets;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private AnonymousFacade anonymousFacade = new AnonymousFacade();

    //get all customer tickets
    @GetMapping("/")
    public List<Tickets> getTickets(Authentication authentication) {
        CustomerFacade customer = (CustomerFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        return customer.getMyTickets();
    }

    //update customer
    @PutMapping("/")
    public void update(@RequestBody Customers updatedCustomer, Authentication authentication) {
        CustomerFacade customer = (CustomerFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        customer.updateCustomer(updatedCustomer);
    }

    //add ticket to customer
    @PostMapping("/")
    public void addTicket(@RequestBody Tickets ticket, Authentication authentication) {
        CustomerFacade customer = (CustomerFacade) anonymousFacade.login
                (authentication.getName(),
                        authentication.getCredentials().toString()
                );
        customer.addTicket(ticket);
    }

}
