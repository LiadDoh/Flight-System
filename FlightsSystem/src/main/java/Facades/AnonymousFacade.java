package Facades;

import DAO.CountriesDao;
import DAO.CustomersDao;
import DAO.UsersDao;
import Models.AirlineCompanies;
import Models.Customers;
import Models.Users;
public class AnonymousFacade extends FacadeBase {


    //Login method for anonymous users returns the login token
    public FacadeBase login(String userName,String password){
        UsersDao dao = new UsersDao();
        Users user = dao.getByUsername(userName);
        if(user!=null && user.password.equals(password)){
            switch (user.user_role){
                case 1:
                    return new CustomerFacade(new LoginToken(user.id,user.username,user.user_role));
                case 2:
                    return new AdministratorFacade(new LoginToken(user.id,user.username,user.user_role));
                case 3:
                    return new AirlineFacade(new LoginToken(user.id,user.username,user.user_role));
            }
        }
        throw new IllegalArgumentException("Invalid username or password");
    }

    //Add new customer
    public void addCustomer(Customers customer){
        CustomersDao dao = new CustomersDao();
        if(!checkInvalidCustomer(customer)){
            return;
        }
        dao.add(customer);
    }

    public boolean checkInvalidCustomer(Customers customer) {
        if(customer.firstName.isEmpty() || customer.firstName.length()<2 || customer.firstName.length()>20)
            return false;
        if(customer.lastName.isEmpty() || customer.lastName.length()<2 || customer.lastName.length()>30)
            return false;
        if(customer.address.isEmpty() || customer.address.length()<5 || customer.address.length()>50)
            return false;
        String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
        if(!customer.phoneNo.matches(regexStr))
            return false;
        if(customer.creditCardNo.length()<8 || customer.creditCardNo.length()>20)
            return false;
        if(checkUserId(customer.userId))
            return false;
        return true;

    }

    private boolean checkUserId(long userId) {
        UsersDao dao = new UsersDao();
        Users user = dao.get((int)userId);
        if(user!=null||user.user_role!=1)
            return true;
        return false;
    }

    public boolean checkInvalidAirline(AirlineCompanies airlineCompany) {
        if (airlineCompany.name == null || airlineCompany.name.isEmpty()) {
            return false;
        }
        if (airlineCompany.countryId > 0) {
            CountriesDao countriesDao = new CountriesDao();
            if (countriesDao.get(airlineCompany.countryId) == null) {
                return false;
            }
            return false;
        }
        if (airlineCompany.userId > 0) {
            UsersDao usersDao = new UsersDao();
            if (usersDao.get((int) airlineCompany.userId) == null) {
                return false;
            }
        }
        return true;
    }
}
