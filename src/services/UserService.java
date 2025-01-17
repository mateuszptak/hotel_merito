package services;

import models.Guest;
import models.Hotel;
import models.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
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
                room.isClean();

                // random date leave room
                Random random = new Random();
                int stayDuration = random.nextInt(3) + 1;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(stayDuration));
                calendar.add(Calendar.DAY_OF_YEAR, stayDuration);
                room.setCheckOutDate(calendar.getTime());

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
                room.setClean(false);
                return true;
            }
        }
        return false;
    }

    // display all dirty rooms
    public void displayAllDirtyRooms() {
        List<Room> dirtyRooms = hotel.getRooms().stream()
                .filter(room -> !room.isClean())
                .collect(Collectors.toList());
        if (dirtyRooms.isEmpty()) {
            System.out.println("Brak brudnych pokoi.");
        } else {
            dirtyRooms.forEach(System.out::println);
        }
    }

    public void displayOccupiedRoomsWithLeavingDate() {
        List<Room> occupiedRooms = hotel.getRooms().stream()
                .filter(room -> !room.isAvailable())
                .collect(Collectors.toList());
        if (occupiedRooms.isEmpty()) {
            System.out.println("Brak zajętych pokoi.");
        } else {
            occupiedRooms.forEach(room -> {
                System.out.println("Pokój numer " + room.getRoomNumber() + " jest zajęty do " + room.getCheckOutDate());
            });
        }
    }

}