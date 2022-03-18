import DAO.*;
import Models.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FlightsDao dao = new FlightsDao();
        List<Flights> x = dao.getAll();
        for (Flights c : x) {
            System.out.println(c);
        }
        Flights c1 = dao.get(6);
        dao.delete(c1);
        x = dao.getAll();
        for (Flights c : x) {
            System.out.println(c);
        }
    }
}
