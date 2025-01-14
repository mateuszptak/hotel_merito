import models.Guest;
import models.Hotel;
import services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

            // add exception handling for invalid input
            while (true) {
                try {
                    choice = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Proszę wprowadzić poprawny numer opcji.");
                    scanner.next();
                }
            }

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
                    int roomNumberToReserve;

                    // exception handling for invalid input
                    while (true) {
                        try {
                            System.out.print("Podaj numer pokoju do rezerwacji: ");
                            roomNumberToReserve = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Proszę wprowadzić poprawny numer pokoju.");
                            scanner.next();
                        }
                    }

                    // list of guests
                    List<Guest> guests = new ArrayList<>();
                    int numberOfGuests;
                    // exception handling for invalid input
                    while (true) {
                        try {
                            System.out.print("Podaj liczbę gości: ");
                            numberOfGuests = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Proszę wprowadzić poprawną liczbę gości.");
                            scanner.next();
                        }
                    }
                    // loop to add new guests
                    for (int i = 0; i < numberOfGuests; i++) {
                        System.out.print("Podaj imię gościa: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Podaj nazwisko gościa: ");
                        String lastName = scanner.nextLine();
                        LocalDate birthDay;
                        while (true) {
                            // exception handling for invalid date format
                            try {
                                System.out.print("Podaj datę urodzenia gościa (YYYY-MM-DD): ");
                                birthDay = LocalDate.parse(scanner.nextLine());
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Proszę wprowadzić datę w formacie YYYY-MM-DD.");
                            }
                        }
                        guests.add(new Guest(firstName, lastName, birthDay));
                    }

                    // exception handling for reserveRoom method

                    try {
                        if (userService.reserveRoom(roomNumberToReserve, guests)) {
                            System.out.println("Pokój numer " + roomNumberToReserve + " został zarezerwowany.");
                        } else {
                            System.out.println("Pokój numer " + roomNumberToReserve
                                    + " jest zajęty lub brak pełnoletniego gościa.");
                        }
                    } catch (Exception e) {
                        System.out.println("Wystąpił błąd podczas rezerwacji pokoju: " + e.getMessage());
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