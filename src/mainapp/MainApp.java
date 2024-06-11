package mainapp;

import java.util.*;
import authentication.UserAuthentication;

public class MainApp {
    // Using private static final to ensure a single, unmodifiable instance of Scanner within this class
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            handleUserChoice(choice);
        } while (choice != 4);
        sc.close();
        System.out.println("Thank you for your visit. Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("Login");
        System.out.println("Choose your profile:");
        System.out.println("1. Login to your Customer account");
        System.out.println("2. Login to your Admin account");
        System.out.println("3. Create a new Customer account");
        System.out.println("4. Close the program");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            System.out.println("Oops! It seems there was an error in your input. Please choose a number.");
            sc.nextLine(); // Consume the invalid input
        }
        return choice;
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                UserAuthentication.customerLoginAndActions(sc);
                break;
            case 2:
                UserAuthentication.adminLoginAndActions(sc);
                break;
            case 3:
                UserAuthentication.createNewCustomerAccount(sc);
                break;
            case 4:
                // Exit the loop in main method
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
