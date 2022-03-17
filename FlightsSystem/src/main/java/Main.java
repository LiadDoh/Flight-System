import DAO.AdministratorsDao;
import DAO.CountriesDao;
import Models.Administrators;
import Models.Countries;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AdministratorsDao dao = new AdministratorsDao();
        List<Administrators> admin = dao.getAll();
        for (Administrators a : admin) {
            System.out.println(a);
        }
        Administrators admin1 = dao.get(3);
        dao.delete(admin1);
    }
}
