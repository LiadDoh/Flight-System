package com.FlightsSystem.Models;

public class Administrators implements POCO{

    public int id;
    public String firstName;
    public String lastName;
    public long userId;

    public Administrators(int id, String firstName, String lastName, long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
