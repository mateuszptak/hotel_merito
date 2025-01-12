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
}