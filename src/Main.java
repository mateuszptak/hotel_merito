import models.Guest;
import models.Hotel;
import services.HotelService;
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
        HotelService hotelService = new HotelService(hotel);
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
                    while (true) {
                        int roomNumberToReserve;

                        // exception handling for invalid input
                        while (true) {
                            try {
                                System.out.print("Podaj numer pokoju do rezerwacji (lub 0, aby anulować): ");
                                roomNumberToReserve = scanner.nextInt();
                                scanner.nextLine();
                                if (roomNumberToReserve == 0) {
                                    System.out.println("Rezerwacja anulowana. Rozpocznij od nowa.");
                                    break;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Proszę wprowadzić poprawny numer pokoju.");
                                scanner.next();
                            }
                        }

                        if (roomNumberToReserve == 0) {
                            break;
                        }

                        // list of guests
                        List<Guest> guests = new ArrayList<>();
                        int numberOfGuests;

                        // exception handling for invalid input
                        while (true) {
                            try {
                                System.out.print("Podaj liczbę gości (maksymalnie 4, lub 0, aby anulować): ");
                                numberOfGuests = scanner.nextInt();
                                scanner.nextLine();
                                if (numberOfGuests == 0) {
                                    System.out.println("Rezerwacja anulowana. Rozpocznij od nowa.");
                                    break;
                                }
                                if (numberOfGuests > 4) {
                                    System.out.println(
                                            "Maksymalna liczba gości w pokoju to 4. Proszę wprowadzić poprawną liczbę.");
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Proszę wprowadzić poprawną liczbę gości.");
                                scanner.next();
                            }
                        }

                        if (numberOfGuests == 0) {
                            break;
                        }

                        // loop to get guest details
                        for (int i = 0; i < numberOfGuests; i++) {
                            System.out.print("Podaj imię gościa (lub 0, aby anulować): ");
                            String firstName = scanner.nextLine();
                            if (firstName.equals("0")) {
                                System.out.println("Rezerwacja anulowana. Rozpocznij od nowa.");
                                break;
                            }
                            System.out.print("Podaj nazwisko gościa (lub 0, aby anulować): ");
                            String lastName = scanner.nextLine();
                            if (lastName.equals("0")) {
                                System.out.println("Rezerwacja anulowana. Rozpocznij od nowa.");
                                break;
                            }
                            LocalDate birthDay = null;

                            // exception handling for invalid input
                            while (true) {
                                try {
                                    System.out.print("Podaj datę urodzenia gościa (YYYY-MM-DD, lub 0, aby anulować): ");
                                    String input = scanner.nextLine();
                                    if (input.equals("0")) {
                                        System.out.println("Rezerwacja anulowana. Rozpocznij od nowa.");
                                        break;
                                    }
                                    birthDay = LocalDate.parse(input);
                                    break;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Proszę wprowadzić datę w formacie YYYY-MM-DD.");
                                }
                            }

                            if (birthDay == null) {
                                break;
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
                    }
                    break;
                case 4:
                    int roomNumberToRelease;

                    // exception handling for invalid input
                    while (true) {
                        try {
                            System.out.print("Podaj numer pokoju który chcesz zwolnić: ");
                            roomNumberToRelease = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Proszę wprowadzić poprawny numer pokoju.");
                            scanner.next();
                        }
                    }

                    // exception handling for releaseRoom method
                    try {
                        if (userService.releaseRoom(roomNumberToRelease)) {
                            System.out.println("Pokój numer " + roomNumberToRelease + " został zwolniony.");
                        } else {
                            System.out.println("Pokój numer " + roomNumberToRelease + " jest wolny.");
                        }
                    } catch (Exception e) {
                        System.out.println("Wystąpił błąd podczas zwalniania pokoju: " + e.getMessage());
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