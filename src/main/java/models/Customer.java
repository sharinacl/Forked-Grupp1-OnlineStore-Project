package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilities.CustomerGender;

/**
 * To make a unique customer.
 * 
 * @author Jessica Olofsson
 */
public class Customer {
    private static int newOrderID = 1;
    private int orderID;
    private static int newID = 1;
    private int customerID;
    private String name;
    private String email;
    private Map<Integer, List<Product>> orders;
    private CustomerGender gender;
    private ShoppingCart cart;

    /**
     * Makes a unique customer.
     * 
     * @param name   Name of the customer.
     * @param email  Email of the cusotmer.
     * @param gender Gender of customer.
     * @param cart   Shoppingcart to put products into.
     */
    public Customer(String name, String email, String gender, ShoppingCart cart) {
        this.customerID = newID++;
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank");
        }
        this.name = name;

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Not a valid email");
        }
        this.email = email;

        boolean match = false;
        for (CustomerGender genderType : CustomerGender.values()) {
            if (genderType.toString().equals(gender)) {
                this.gender = CustomerGender.valueOf(gender);
                match = true;
                break;
            }
        }
        if (!match) {
            throw new IllegalArgumentException("Not a vaild gender chose");
        }

        orders = new HashMap<>();
        this.cart = cart;
    }

    /**
     * Takes a string check if the gender is equal to the CustomerGender.
     * 
     * @param gender the gender the user put in.
     * @return returns the gender from CustomerGender enum.
     */
    public static CustomerGender getGenderFromString(String gender) {
        try {
            return CustomerGender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ogiltig könstyp: " + gender);
            return null;
        }
    }

    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * For the customer to be able to see order history.
     */
    public void viewOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No history was found");
        } else {
            double totalCost = 0;
            for (Map.Entry<Integer, List<Product>> o : orders.entrySet()) {
                System.out.printf("Order number: %d%nProducts: %n", o.getKey());
                for (Product product : o.getValue()) {
                    System.out.println(" - " + product);
                    totalCost += product.calculateCost();
                }
                System.out.printf("Total cost of order: %.2f%n%n", totalCost);
            }
        }
    }

    /**
     * For the customer to place an order..
     * 
     */
    public void placeOrder() {
        if (cart.getProductsInCart().isEmpty()) {
            System.out.println("Can't place an order. Theres nothing in the cart");
        }
        this.orderID = newOrderID++;
        List<Product> completedorder = new ArrayList<>();
        completedorder.addAll(cart.getProductsInCart());
        orders.put(orderID, completedorder);
        cart.emptyCart();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CustomerGender getCustomerGender() {
        return gender;
    }
}
