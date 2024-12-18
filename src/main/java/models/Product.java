package models;

import java.util.Objects;

public class Product {
    private static int nextId = 1;
    private int productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.productId = nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product createForCart(int quantity) {
        return new Product(this.productId, this.name, this.price, quantity);
    }

    public void takeFromShelf(int qty) {
        quantity -= qty;
    }

    public void putBackOnShelf(int qty) {
        quantity += qty;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateCost() {
        return price * quantity;
    }

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
        return Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                name.equals(product.name);
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