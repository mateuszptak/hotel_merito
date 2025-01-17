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
            if (room.getRoomNumber() == roomNumber && !room.isAvailable()) {
                room.isClean();
                System.out.println("Pokój nr " + roomNumber + " został posprzątany.");
            }
        }
    }

}