package DAO;

import Models.Countries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesDao implements DAO<Countries> {

    private final String TABLE_NAME = "Countries";
    private List<Countries> countries = new ArrayList<>();
    private Repository repository = new Repository();
    private Connection connection = repository.getConnection();
    private Statement statement = repository.getStatement();
    private ResultSet rs = null;

    @Override
    public Countries get(int id) {
        Countries country = null;
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\" WHERE id = " + id);
            rs.next();
            country = new Countries(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBytes("flag")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public List<Countries> getAll() {
        try {
            rs = statement.executeQuery("SELECT * FROM \"" + TABLE_NAME + "\"");
            while (rs.next()) {
                Countries country = new Countries(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBytes("flag")
                );
                countries.add(country);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public void add(Countries country) {
        try {
            rs = statement.executeQuery("INSERT INTO \"" + TABLE_NAME + "\" (name, flag) VALUES ('" + country.name + "', '" + country.flag + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Countries country) {
        try {
            rs = statement.executeQuery("UPDATE \"" + TABLE_NAME + "\" SET name = '" + country.name + "', flag = '" + country.flag + "' WHERE id = " + country.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Countries country) {
        try {
            rs = statement.executeQuery("DELETE FROM \"" + TABLE_NAME + "\" WHERE id = " + country.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}