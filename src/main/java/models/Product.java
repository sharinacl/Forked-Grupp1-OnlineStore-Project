package models;

import java.util.Objects;

/**
 * @author Sharina Chavez-Lissjanis
 * Represents a product in the system, which can be added to a shopping cart or managed in inventory.
 * Each product has a unique ID, name, price, and quantity.
 */
public class Product {
    private static int nextId = 1;
    private int productId;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new product with the given name, price, and quantity.
     * The product ID is automatically incremented for each new product.
     *
     * @param name     The name of the product.
     * @param price    The price of the product.
     * @param quantity The initial quantity of the product.
     */
    public Product(String name, double price, int quantity) {
        this.productId = nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Private constructor used to create a product for the shopping cart.
     * This constructor is used internally to create a product with a specific quantity.
     *
     * @param productId The ID of the product.
     * @param name      The name of the product.
     * @param price     The price of the product.
     * @param quantity  The quantity of the product in the cart.
     */
    private Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Creates a new product instance for the shopping cart with a specific quantity.
     * @param quantity The quantity of the product to be added to the cart.
     * @return A new Product instance ant its specified quantity.
     */
    public Product createForCart(int quantity) {
        return new Product(this.productId, this.name, this.price, quantity);
    }

    /**
     * Reduces the quantity of the product on the shelf.
     * @param qty The quantity to be taken from the shelf.
     */
    public void takeFromShelf(int qty) {
        quantity -= qty;
    }

    /**
     * Increases the quantity of the product on the shelf.
     *
     * @param qty The quantity to be put back on the shelf.
     */
    public void putBackOnShelf(int qty) {
        quantity += qty;
    }

    /**
     *
     * @return cost of the item.
     */
    public double calculateCost() {
        return price * quantity;
    }

    /**
     *
     * @return productID
     */
    public int getProductId() {
        return productId;
    }

    /**
     *
     * @return product name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return product quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity new quantity to be given so the object
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d Name: %s Price: %.2f Quantity: %d", productId, name, price, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }

    // Add a method to reset the nextId for testing purposes
    public static void resetNextId() {
        nextId = 1;
    }
}