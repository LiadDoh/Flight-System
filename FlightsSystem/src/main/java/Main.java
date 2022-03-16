import DAO.CountriesDao;
import Models.Countries;

public class Main {

    public static void main(String[] args) {
        CountriesDao dao = new CountriesDao();
        Countries country = dao.get(1);
        System.out.println(country);
    }
}
