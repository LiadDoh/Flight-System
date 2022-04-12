package com.FlightsSystem.Models;

public class Tickets implements POCO {

    public long id;
    public long flightId;
    public long customerId;

    public Tickets(long id, long flightId, long customerId) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId='" + flightId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
