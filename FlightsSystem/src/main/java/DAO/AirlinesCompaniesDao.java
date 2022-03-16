package DAO;

import Models.AirlineCompanies;
import Models.Customers;

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

    @Override
    public AirlineCompanies get(int id) {
        AirlineCompanies airlineCompany = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            airlineCompany = new AirlineCompanies(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("countryId"),
                    rs.getLong("userId")

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }

    @Override
    public List<AirlineCompanies> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                AirlineCompanies airlineCompany = new AirlineCompanies(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("countryId"),
                        rs.getLong("userId")

                );
                airlineCompanies.add(airlineCompany);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airlineCompanies;
    }

    @Override
    public void add(AirlineCompanies airlineCompany) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (name, countryId, userId) VALUES ('" + airlineCompany.name + "', " + airlineCompany.countryId + ", " + airlineCompany.userId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AirlineCompanies airlineCompany) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET name = '" + airlineCompany.name + "', countryId = " + airlineCompany.countryId + ", userId = " + airlineCompany.userId + " WHERE id = " + airlineCompany.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AirlineCompanies airlineCompany) {
//        try {
//            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + countries.id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
