package services;

import models.Hotel;
import models.Room;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private Hotel hotel;

    public UserService(Hotel hotel) {
        this.hotel = hotel;
    }

    // fetching all rooms
    public List<Room> getAllRooms() {
        return hotel.getRooms();
    }

    // fetching all empty rooms
    public List<Room> getAvailableRooms() {
        return hotel.getRooms().stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    // reverve a room
    public boolean reserveRoom(int roomNumber) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    // release a room
    public boolean releaseRoom(int roomNumber) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber && !room.isAvailable()) {
                room.setAvailable(true);
                return true;
            }
        }
        return false;
    }
}