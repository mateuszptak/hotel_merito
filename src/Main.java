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
        } while (choice != 5);

        scanner.close();
    }
}