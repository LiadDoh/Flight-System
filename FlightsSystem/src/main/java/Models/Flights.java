package Models;

import java.time.LocalDateTime;

public class Flights implements POCO{

    public long id;
    public long airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public LocalDateTime departureTime;
    public LocalDateTime landingTime;
    public int remainingTickets;

    public Flights(long id, long airlineCompanyId, int originCountryId, int destinationCountryId, LocalDateTime departureTime, LocalDateTime landingTime, int remainingTickets) {
        this.id = id;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompanyId='" + airlineCompanyId + '\'' +
                ", originCountryId='" + originCountryId + '\'' +
                ", destinationCountryId='" + destinationCountryId + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", landingTime='" + landingTime + '\'' +
                ", remainingTickets='" + remainingTickets + '\'' +
                '}';
    }
}
