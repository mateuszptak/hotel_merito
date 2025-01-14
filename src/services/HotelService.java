package services;

import models.Hotel;
import models.Room;

public class HotelService {
    private Hotel hotel;

    public HotelService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void cleanRoom(int roomNumber) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber) {
                room.setClean(true);
                System.out.println("Pokój numer " + roomNumber + " został posprzątany.");
                return;
            }
        }
        System.out.println("Pokój numer " + roomNumber + " nie został znaleziony.");
    }
}