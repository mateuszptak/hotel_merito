package services;

import models.Guest;
import models.Hotel;
import models.Room;

import java.time.LocalDate;
import java.time.Period;
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

    // reverve a room with guests
    public boolean reserveRoom(int roomNumber, List<Guest> guests) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                boolean hasAdult = guests.stream()
                        .anyMatch(guest -> Period.between(guest.getBirthDay(), LocalDate.now())
                                .getYears() >= 18);

                if (!hasAdult) {
                    System.out.println("Przynajmniej jeden gość musi być pełnoletni.");
                    return false;
                }

                for (Guest guest : guests) {
                    room.addGuest(guest);
                }
                room.setAvailable(false);
                room.isClean(false);
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

    // display all dirty rooms
    public void displayAllDirtyRooms() {
        List<Room> dirtyRooms = hotel.getRooms().stream()
                .filter(room -> !room.isClean(true))
                .collect(Collectors.toList());
        if (dirtyRooms.isEmpty()) {
            System.out.println("Brak brudnych pokoi.");
        } else {
            dirtyRooms.forEach(System.out::println);
        }
    }

}