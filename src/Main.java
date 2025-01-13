import models.Hotel;
import services.UserService;

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
                    System.out.println("Podaj numer pokoju do rezerwacji: ");
                    int roomNumberToReserve = scanner.nextInt();
                    if (userService.reserveRoom(roomNumberToReserve)) {
                        System.out.println("Pokój numer " + roomNumberToReserve + " został zarezerwowany.");
                    } else {
                        System.out.println("Pokój numer " + roomNumberToReserve + " jest zajęty.");
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