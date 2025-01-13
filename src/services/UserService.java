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

                boolean isAdult = guests.stream()
                        .anyMatch(guest -> Period.between(guest.getBirthDay
                        // TODO: problem z przekazaniem daty urodzenia

                        (null), LocalDate.now()).getYears() >= 18);

                if (isAdult == false) {
                    System.out.println("Brak pełnoletniej osoby. Rezerwacja nie powiodła się.");
                    return false;
                }

                for (Guest guest : guests) {
                    room.addGuest(guest);
                }

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