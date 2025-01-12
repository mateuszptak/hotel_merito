package models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
        for (int i = 1; i <= 69; i++) {
            rooms.add(new Room(i, 2, true, true));
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }
}