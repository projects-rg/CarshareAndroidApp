package com.example.carshareprototype;

public class Lists {
    String startingAddress;
    String destination;
    String seatsAvailable;
    String contactNumber;

    public Lists(String startingAddress, String destination, String seatsAvailable, String contactNumber) {
        this.startingAddress = startingAddress;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
        this.contactNumber = contactNumber;
    }

    public String getStartingAddress() {
        return startingAddress;
    }

    public String getDestination() {
        return destination;
    }

    public String getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
