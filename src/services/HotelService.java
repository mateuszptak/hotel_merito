package services;

import models.Hotel;
import models.Room;

public class HotelService {
    private Hotel hotel;

    public HotelService(Hotel hotel) {
        this.hotel = hotel;
    }

    // cleaning room method
    public void cleanRoom(int roomNumber) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber) {
                if (!room.isClean()) {
                    room.setClean(true);
                    System.out.println("Pokój numer " + roomNumber + " został posprzątany.");
                } else {
                    System.out.println("Pokój numer " + roomNumber + " jest już czysty.");
                }
                return;
            }
        }
        System.out.println("Pokój numer " + roomNumber + " nie istnieje.");
    }

}