import models.Guest;
import models.Hotel;
import services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Hotel hotel = new Hotel();
        UserService userService = new UserService(hotel);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Wyświetl listę pokoi wraz z ich statusem");
            System.out.println("2. Wyświetl listę dostępnych pokoi");
            System.out.println("3. Zarezerwuj pokój");
            System.out.println("4. Zwolnij pokój");
            System.out.println("5. Zakończ");
            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userService.getAllRooms().forEach(room -> System.out.println("Pokój " + room.getRoomNumber()
                            + " (status: " + (room.isAvailable() ? "wolny" : "zajęty") + ")"));
                    break;
                case 2:
                    userService.getAvailableRooms()
                            .forEach(room -> System.out.println("Pokój " + room.getRoomNumber()));
                    break;
                case 3:
                    System.out.print("Podaj numer pokoju do rezerwacji: ");
                    int roomNumberToReserve = scanner.nextInt();
                    scanner.nextLine();

                    // list of guests
                    List<Guest> guests = new ArrayList<>();

                    // number of guests
                    System.out.print("Podaj liczbę gości: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine();

                    // loop to add new guests
                    for (int i = 0; i < numberOfGuests; i++) {
                        System.out.print("Podaj imię gościa: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Podaj nazwisko gościa: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Podaj datę urodzenia gościa (YYYY-MM-DD): ");
                        LocalDate birthDay = LocalDate.parse(scanner.nextLine());
                        guests.add(new Guest(firstName, lastName, birthDay));
                    }

                    // room reservation
                    if (userService.reserveRoom(roomNumberToReserve, guests)) {
                        System.out.println("Pokój numer " + roomNumberToReserve + " został zarezerwowany.");
                    } else {
                        System.out.println(
                                "Pokój numer " + roomNumberToReserve + " jest zajęty lub brak pełnoletniego gościa.");
                    }
                    break;
                case 4:
                    System.out.println("Podaj numer pokoju który chcesz zwolnić:");
                    int roomNumberToRelease = scanner.nextInt();
                    if (userService.releaseRoom(roomNumberToRelease)) {
                        System.out.println("Pokój numer " + roomNumberToRelease + " został zwolniony.");
                    } else {
                        System.out.println("Pokój numer " + roomNumberToRelease + " jest wolny.");
                    }
                    break;
                case 5:
                    System.out.println("Dziękujemy za korzystanie z naszego systemu.\nDo zobaczenia!");
                    break;

                default:
                    System.out.println("Nieprawidłowa opcja.");
            }

        } while (choice != 5);

        scanner.close();
    }
}