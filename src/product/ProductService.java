package product;

import java.util.*;

public class ProductService {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Toy Car", 15.00, ProductCategory.TOYS_KIDS));
        products.add(new Product(2, "Doll", 12.50, ProductCategory.TOYS_KIDS));

        products.add(new Product(3, "Football", 25.00, ProductCategory.SPORTS_OUTDOORS));
        products.add(new Product(4, "Camping Tent", 150.00, ProductCategory.SPORTS_OUTDOORS));

        products.add(new Product(5, "Garden Hose", 20.00, ProductCategory.HOME_GARDEN));
        products.add(new Product(6, "Plant Pot", 10.50, ProductCategory.HOME_GARDEN));

        products.add(new Product(7, "Shampoo", 8.00, ProductCategory.HEALTH_BEAUTY));
        products.add(new Product(8, "Face Cream", 15.50, ProductCategory.HEALTH_BEAUTY));

        products.add(new Product(9, "Chocolate Bar", 3.50, ProductCategory.FOOD_BEVERAGES));
        products.add(new Product(10, "Coffee Beans", 12.00, ProductCategory.FOOD_BEVERAGES));

        products.add(new Product(11, "T-shirt", 18.00, ProductCategory.FASHION));
        products.add(new Product(12, "Sneakers", 50.00, ProductCategory.FASHION));

        products.add(new Product(13, "Bluetooth Speaker", 45.00, ProductCategory.ELECTRONICS));
        products.add(new Product(14, "Smartphone", 300.00, ProductCategory.ELECTRONICS));

        products.add(new Product(15, "Novel", 15.00, ProductCategory.BOOKS_MEDIA));
        products.add(new Product(16, "Movie DVD", 10.00, ProductCategory.BOOKS_MEDIA));

        products.add(new Product(17, "Car Air Freshener", 5.50, ProductCategory.AUTOMOTIVE_ACCESSORIES));
        products.add(new Product(18, "Car Charger", 12.00, ProductCategory.AUTOMOTIVE_ACCESSORIES));

        products.add(new Product(19, "Microwave Oven", 120.00, ProductCategory.APPLIANCES));
        products.add(new Product(20, "Blender", 40.00, ProductCategory.APPLIANCES));
    }

    public static void allAdminActions(Scanner sc) {
        try {
            Product chosenProduct = filterProductsByCategory(sc);
            if (chosenProduct == null)
                return;

            System.out.println("Selected product: " + chosenProduct);
            performProductAction(sc, chosenProduct);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void addToCart(Scanner sc, ShoppingCart cart) {
        try {
            Product chosenProduct = filterProductsByCategory(sc);
            if (chosenProduct == null)
                return;

            System.out.println("Product Information:");
            System.out.println("Name: " + chosenProduct.getName());
            System.out.println("Price: Rs" + chosenProduct.getPrice());
            System.out.println("Category: " + chosenProduct.getCategory());

            System.out.println("Choose a function:");
            System.out.println("1. Add the product to my cart");
            System.out.println("2. Go back");
            int functionChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (functionChoice) {
                case 1:
                    System.out.print("How many " + chosenProduct.getName() + " do you want to buy? ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < quantity; i++) {
                        cart.addItems(chosenProduct);
                    }
                    System.out.println("Product added to the cart.");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Returning to customer menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static Product filterProductsByCategory(Scanner sc) {
        try {
            ProductCategory chosenCategory = chooseCategory(sc);
            if (chosenCategory == null)
                return null;

            List<Product> filteredProducts = filterProductsByCategory(chosenCategory);

            if (filteredProducts.isEmpty()) {
                System.out.println("No products available in this category.");
                return null;
            }

            Product chosenProduct = chooseProduct(sc, filteredProducts);
            return chosenProduct;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
            return null;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    private static ProductCategory chooseCategory(Scanner sc) {
        try {
            System.out.println("Available categories: ");
            for (ProductCategory category : ProductCategory.values()) {
                System.out.println((category.ordinal() + 1) + ". " + category);
            }
            System.out.println("Choose a category by entering its number:");
            int categoryChoice = sc.nextInt();
            sc.nextLine();
            if (categoryChoice < 1 || categoryChoice > ProductCategory.values().length) {
                System.out.println("Invalid category choice. Returning to menu.");
                return null;
            }

            return ProductCategory.values()[categoryChoice - 1];
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
            return null;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    private static List<Product> filterProductsByCategory(ProductCategory category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    private static Product chooseProduct(Scanner sc, List<Product> filteredProducts) {
        try {
            System.out.println("Available products:");
            for (int i = 0; i < filteredProducts.size(); i++) {
                System.out.println((i + 1) + ". " + filteredProducts.get(i));
            }
            System.out.println("Choose a product by entering its number:");
            int productChoice = sc.nextInt();
            sc.nextLine();

            if (productChoice < 1 || productChoice > filteredProducts.size()) {
                System.out.println("Invalid product choice. Returning to menu.");
                return null;
            }
            return filteredProducts.get(productChoice - 1);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
            return null;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    private static void performProductAction(Scanner sc, Product product) {
        try {
            System.out.println("Choose a function:");
            System.out.println("1. Update the product information");
            System.out.println("2. Remove product");
            System.out.println("3. Go back");
            int functionChoice = sc.nextInt();
            sc.nextLine();
            switch (functionChoice) {
                case 1:
                    updateProduct(sc, product);
                    break;
                case 2:
                    removeProduct(product);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void updateProduct(Scanner sc, Product product) {
        try {
            System.out.println("Select what you want to update:");
            System.out.println("1. The price");
            System.out.println("2. The name of the product");
            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.print("Enter the new price for the product " + product.getName() + ": ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    product.setPrice(newPrice);
                    System.out.println("Product price updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter the new name for the product " + product.getName() + ": ");
                    String newName = sc.nextLine();
                    product.setName(newName);
                    System.out.println("Product name updated successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void removeProduct(Product product) {
        products.remove(product);
        System.out.println("Product removed successfully!");
    }

    public static void addProduct(Scanner sc) {
        try {
            System.out.println("Give me the id of the product");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Give me the name of the product");
            String name = sc.nextLine();
            System.out.println("Give me the price of the product");
            double price = sc.nextDouble();
            sc.nextLine();
            ProductCategory chosenCategory = chooseCategory(sc);
            if (chosenCategory == null)
                return;

            Product newProduct = new Product(id, name, price, chosenCategory);
            products.add(newProduct);
            System.out.println("The product has been added successfully in category " + chosenCategory);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void viewAllProducts() {
        System.out.println("All Products: ");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void viewCartAndCheckout(Scanner sc, ShoppingCart cart) {
        try {
            cart.viewCart();

            System.out.println("Choose a function:");
            System.out.println("1. Proceed to checkout");
            System.out.println("2. Go back");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    checkout(cart);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void checkout(ShoppingCart cart) {
        double total = cart.calculateTotal();
        System.out.println("Total payable amount: Rs" + total);
        cart.clearCart();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }
}
