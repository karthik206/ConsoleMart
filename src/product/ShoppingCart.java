package product;

import java.util.*;

public class ShoppingCart {
    List<Product> cartItems =new ArrayList<>();

    public void addItems(Product product) {
        cartItems.add(product);
    }

    public void viewCart() {
        if(cartItems.isEmpty())
            System.out.println("Your cart is empty.");
        else {
            System.out.println("Your cart contains:");
            for (Product product:cartItems) {
                System.out.println(product);
            }
        }
    }
    public double calculateTotal() {
        double total = 0.0;
        for (Product product: cartItems) {
            total+=product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
