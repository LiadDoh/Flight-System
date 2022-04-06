package Controllers;

import Facades.AnonymousFacade;
import Facades.CustomerFacade;
import Facades.LoginToken;
import Models.Customers;
import Models.Tickets;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {


    @GetMapping("/tickets")
    public List<Tickets> getTickets(LoginToken token) {
        CustomerFacade customer = new CustomerFacade(token);
        return customer.getMyTickets();
    }

    @PutMapping("/update")
    public void update(@RequestBody Customers updatedCustomer, LoginToken token) {
        CustomerFacade customer = new CustomerFacade(token);
        customer.updateCustomer(updatedCustomer);
    }

    @PostMapping("/addTicket")
    public void addTicket(@RequestBody Tickets ticket, LoginToken token) {
        CustomerFacade customer = new CustomerFacade(token);
        customer.addTicket(ticket);
    }

}
