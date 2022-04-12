package com.FlightsSystem.Facades;

import com.FlightsSystem.DAO.AdministratorsDao;
import com.FlightsSystem.DAO.AirlinesCompaniesDao;
import com.FlightsSystem.DAO.CustomersDao;
import com.FlightsSystem.DAO.UsersDao;
import com.FlightsSystem.Models.Administrators;
import com.FlightsSystem.Models.AirlineCompanies;
import com.FlightsSystem.Models.Customers;

import java.util.List;

public class AdministratorFacade extends AnonymousFacade{

    private LoginToken token;

    public AdministratorFacade(LoginToken loginToken) {
        super();
    }

    //Get all customers from the database
    public List<Customers> getAllCustomers() {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        CustomersDao customersDao = new CustomersDao();
        return customersDao.getAll();
    }

    //Add new airline company to the database
    public void addAirline(AirlineCompanies airlineCompany) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        if(checkInvalidAirline(airlineCompany))
            throw new IllegalArgumentException("Invalid Airline");
        if(!checkUserExist(airlineCompany.userId))
            throw new IllegalArgumentException("User does not exist");
        AirlinesCompaniesDao airlineCompaniesDao = new AirlinesCompaniesDao();
        airlineCompaniesDao.add(airlineCompany);
    }

    //Add new customer to the database
    public void addCustomer(Customers customer) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        if(!checkInvalidCustomer(customer))
            throw new IllegalArgumentException("Invalid Customer");
        if(!checkUserExist(customer.userId))
            throw new IllegalArgumentException("User does not exist");
        CustomersDao customersDao = new CustomersDao();
        customersDao.add(customer);
        }

    //Add new administrator to the database
    public void addAdministrator(Administrators administrator) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        if(!checkInvalidAdministrator(administrator))
            throw new IllegalArgumentException("Invalid Administrator");
        AdministratorsDao administratorsDao = new AdministratorsDao();
        administratorsDao.add(administrator);
    }

    //Remove airline company from the database
    public void removeAirline(AirlineCompanies airlineCompany) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        AirlinesCompaniesDao airlineCompaniesDao = new AirlinesCompaniesDao();
        airlineCompaniesDao.delete(airlineCompany);
    }

    //Remove customer from the database
    public void removeCustomer(Customers customer) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        CustomersDao customersDao = new CustomersDao();
        customersDao.delete(customer);
    }

    //Remove administrator from the database
    public void removeAdministrator(Administrators administrator) {
        if(this.token.getRole()!=2)
            throw new IllegalArgumentException("You are not an administrator");
        AdministratorsDao administratorsDao = new AdministratorsDao();
        administratorsDao.delete(administrator);
    }

    private boolean checkInvalidAdministrator(Administrators administrator) {
        if(administrator.firstName.length() < 2 || administrator.firstName ==null)
            return false;
        if(administrator.lastName.length() < 2 || administrator.lastName ==null)
            return false;
        if(checkUserExist(administrator.userId))
            return false;
        return true;
    }

    private boolean checkUserExist(long userId) {
        UsersDao usersDao = new UsersDao();
        return usersDao.get((int)userId) != null;
    }
}
