package authentication;

import product.*;

import java.util.*;


public class UserAuthentication {
    private static List<Customer> customers = new ArrayList<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123$";

    public static void customerLoginAndActions(Scanner sc) {
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        if (loginCustomer(username, password)) {
            System.out.println("Accessing customer functionalities...");
            // Add your customer functionalities here
            ShoppingCart cart = new ShoppingCart();
            customerActions(sc,cart);
        }
    }

    private static void customerActions(Scanner sc, ShoppingCart cart) {
        while (true) {
            System.out.println("Choose a function:");
            System.out.println("1. Filter products by category");
            System.out.println("2. View my cart");
            System.out.println("3. Log out");

            int choice  = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    ProductService.addToCart(sc,cart);
                    break;
                case 2:
                    ProductService.viewCartAndCheckout(sc,cart);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean loginCustomer(String username, String password) {
        for (Customer c : customers) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                System.out.println("Login successful as customer!");
                return true;
            }
        }
        System.out.println("Invalid username or password for customer.");
        return false;
    }

    public static void adminLoginAndActions(Scanner sc) {
        System.out.println("Admin username: ");
        String username = sc.nextLine();
        System.out.println("Admin password: ");
        String password = sc.nextLine();
        if (loginAdmin(username, password)) {
            System.out.println("Login successful as admin!");
            // Add your admin functionalities here
            boolean keepRunning = true;
            while (keepRunning) {
                System.out.println("Choose a function:");
                System.out.println("1. Filter products by category");
                System.out.println("2. Add a new product");
                System.out.println("3. View all products");
                System.out.println("4. Log out");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ProductService.allAdminActions(sc);
                        break;
                    case 2:
                        ProductService.addProduct(sc);
                        break;
                    case 3:
                        ProductService.viewAllProducts();
                        break;
                    case 4:
                        keepRunning=false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static boolean loginAdmin(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return true;
        }
        System.out.println("Invalid username or password for admin.");
        return false;
    }

    public static void createNewCustomerAccount(Scanner sc) {
        try {
            System.out.println("Choose a username: ");
            String username = sc.nextLine();
            System.out.println("Choose a password: ");
            String password = sc.nextLine();

            while (!addCustomer(new Customer(username, password))) {
                System.out.println("Username already taken. Choose another username: ");
                username = sc.nextLine();
            }

            System.out.println("Customer account created successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Oops! It seems there was an error in your input.");
            sc.nextLine(); // Clear the invalid input
        }
    }

    private static boolean addCustomer(Customer customer) {
        for (Customer c : customers) {
            if (c.getUsername().equals(customer.getUsername())) {
                return false;
            }
        }
        customers.add(customer);
        return true;
    }
}
