import DAO.*;
import Models.*;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FlightsDao flightsDao = new FlightsDao();
        List<Flights> flights = flightsDao.getFlightsByAirlineId(1);
        for (Flights flight : flights) {
            System.out.println(flight);
        }
    }
}
