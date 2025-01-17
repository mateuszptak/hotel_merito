package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Room {
    private int roomNumber;
    private int capacity;
    private boolean hasBathroom;
    private boolean isAvailable;
    private List<Guest> guests;
    private boolean isClean;
    private Date checkInDate;
    private Date checkOutDate;

    public Room(int roomNumber, int capacity, boolean hasBathroom, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.hasBathroom = hasBathroom;
        this.isAvailable = isAvailable;
        this.guests = new ArrayList<>();
        this.isClean = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Guest> getGuestsList(Guest guest) {
        return guests;
    }

    public boolean hasBathroom() {
        return hasBathroom;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void removeGuest(Guest guest) {
        guests.remove(guest);
    }

    public void addGuest(Guest guest) {
        if (guests.size() < capacity) {
            guests.add(guest);
        } else {
            System.out.println("Pokój jest pełny.");
        }
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Pokój " + roomNumber + " " + "(Status: " + (isClean ? "Posprzątany" : "Brudny") + ")";
    }
}
