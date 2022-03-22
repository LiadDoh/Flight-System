package DAO;

import Models.AirlineCompanies;
import Models.Customers;
import Models.Users;
import org.apache.catalina.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirlinesCompaniesDao implements DAO<AirlineCompanies> {
    private final String TABLE_NAME = "Airline_Companies";
    private List<AirlineCompanies> airlineCompanies = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    //Get airline company by id
    @Override
    public AirlineCompanies get(int id) {
        AirlineCompanies airlineCompany = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            airlineCompany = new AirlineCompanies(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("country_id"),
                    rs.getLong("user_id")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompany;
    }

    //Get all airline companies
    @Override
    public List<AirlineCompanies> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                AirlineCompanies airlineCompany = new AirlineCompanies(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")

                );
                airlineCompanies.add(airlineCompany);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompanies;
    }

    //Add airline company
    @Override
    public void add(AirlineCompanies airlineCompany) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (name, country_id, user_id) VALUES ('" + airlineCompany.name + "', " + airlineCompany.countryId + ", " + airlineCompany.userId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Update airline company
    @Override
    public void update(AirlineCompanies airlineCompany) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET name = '" + airlineCompany.name + "', country_id = " + airlineCompany.countryId + ", user_id = " + airlineCompany.userId + " WHERE id = " + airlineCompany.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete airline company
    @Override
    public void delete(AirlineCompanies airlineCompany) {
        try {
            FlightsDao flightsDao = new FlightsDao();
            flightsDao.deleteByAirlineCompanyId(airlineCompany.id);
            UsersDao usersDao = new UsersDao();
            Users user = usersDao.get((int)airlineCompany.userId);
            usersDao.delete(user);
            //rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + airlineCompany.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Delete airline company by user id
    public void deleteByUserId(long userId) {
        try {
            AirlineCompanies airlineCompany = getByUserId(userId);
            FlightsDao flightsDao = new FlightsDao();
            flightsDao.deleteByAirlineCompanyId(airlineCompany.userId);
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE user_id = " + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
    }

    //Get airline company by user id
    public AirlineCompanies getByUserId(long userId) {
        AirlineCompanies airlineCompany = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE user_id = " + userId);
            rs.next();
            airlineCompany = new AirlineCompanies(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("country_id"),
                    rs.getLong("user_id")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompany;
    }

    //Get all airline companies by country id
    public List<AirlineCompanies> getAirlinesByCountry(int countryId){
        List<AirlineCompanies> airlineCompaniesByCountries = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE country_id = " + countryId);
            while (rs.next()) {
                AirlineCompanies airlineCompany = new AirlineCompanies(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")

                );
                airlineCompaniesByCountries.add(airlineCompany);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompaniesByCountries;
    }

    //Get airline company by username
    public AirlineCompanies getByUsername(String username) {
        AirlineCompanies airlineCompany = null;
        try {
            rs = statement.executeQuery("SELECT * FROM get_airline_by_username(\'" + username + "\')");
            rs.next();
            airlineCompany = new AirlineCompanies(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("country_id"),
                    rs.getLong("user_id")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompany;
    }

    //Get airline company by parameters
    public List<AirlineCompanies> getByParameters(String name, int countryId) {
        List<AirlineCompanies> airlineCompanies = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM get_airline_by_parameters(\'" + name + "\', " + countryId + ")");
            while (rs.next()) {
                AirlineCompanies airlineCompany = new AirlineCompanies(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")

                );
                airlineCompanies.add(airlineCompany);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.closeConnection();
        return airlineCompanies;
    }
}
