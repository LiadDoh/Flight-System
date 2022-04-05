package com.FlightsSystem.Facades;

import com.FlightsSystem.DAO.CustomersDao;
import com.FlightsSystem.DAO.FlightsDao;
import com.FlightsSystem.DAO.TicketsDao;
import com.FlightsSystem.Models.Customers;
import com.FlightsSystem.Models.Flights;
import com.FlightsSystem.Models.Tickets;

import java.util.List;

public class CustomerFacade extends AnonymousFacade {

    private LoginToken token;

    public CustomerFacade(LoginToken loginToken) {
        super();
    }

    //Update customer information
    public void updateCustomer(Customers customer) {
        if(customer.userId != this.token.getId()) {
            throw new IllegalArgumentException("Invalid customer");
        }
        if (checkInvalidCustomer(customer)) {
            throw new IllegalArgumentException("Invalid customer");
        }
        CustomersDao customersDao = new CustomersDao();
        customersDao.update(customer);
    }

    //Add ticket to customer
    public void addTicket(Tickets ticket) {
        CustomersDao customersDao = new CustomersDao();
        Customers customer = customersDao.get((int)this.token.getId());
        if(ticket.customerId != customer.userId) {
            throw new IllegalArgumentException("Invalid customer");
        }
        if (checkInvalidTicket(ticket)) {
            throw new IllegalArgumentException("Invalid ticket");
        }
        FlightsDao flightsDao = new FlightsDao();
        TicketsDao ticketsDao = new TicketsDao();
        Flights flight = flightsDao.get((int)ticket.flightId);
        flight.remainingTickets--;
        flightsDao.update(flight);
        ticketsDao.add(ticket);
    }

    //Get all tickets of customer
    public List<Tickets> getMyTickets() {
        CustomersDao customersDao = new CustomersDao();
        Customers customer = customersDao.get((int)this.token.getId());
        TicketsDao ticketsDao = new TicketsDao();
        return ticketsDao.getTicketsByCustomer(customer.id);
    }

    private boolean checkInvalidTicket(Tickets ticket) {
        FlightsDao flightsDao = new FlightsDao();
        CustomersDao customersDao = new CustomersDao();
        Flights flight = flightsDao.get((int)ticket.flightId);
        if ( flight == null || flight.remainingTickets<1) {
            return true;
        }
        if (customersDao.get((int)ticket.customerId) == null) {
            return true;
        }
        return false;

    }
}