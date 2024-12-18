package models;

import java.util.*;

public class ShoppingCart {
    private List<Product> productsInCart;

    public ShoppingCart() {
        productsInCart = new ArrayList<>();
    }

    public void addProductToCart(Product p, int qty) {
        int newQuantity = 0;
        boolean productFound = false;
        if (qty <= 0) {
            throw new IllegalArgumentException("Cannot put 0 or negative quantity in cart.");
        } else if (qty > p.getQuantity()) {
            throw new IllegalArgumentException("Cannot put more items in cart than we have in stock.");
        }

        for (Product item : productsInCart) {
            if (p.getProductId() == item.getProductId()) {
                newQuantity = item.getQuantity() + qty;
                p.takeFromShelf(qty);
                productsInCart.remove(item);
                productsInCart.add(p.createForCart(newQuantity));
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            p.takeFromShelf(qty);
            productsInCart.add(p.createForCart(qty));
        }
    }

    public void removeProductFromCart(Product p, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Cannot remove 0 or negative items from cart");
        }
        for (Product item : productsInCart) {
            if (p.getProductId() == item.getProductId()) {
                if (qty > item.getQuantity()) {
                    throw new IllegalArgumentException("Cannot put back more than you have in the cart.");
                } else if (qty == item.getQuantity()) {
                    productsInCart.remove(item);
                    p.putBackOnShelf(qty);
                } else {
                    int ammountOfItemInCart = item.getQuantity();
                    productsInCart.remove(item);
                    productsInCart.add(p.createForCart(ammountOfItemInCart - qty));
                    p.putBackOnShelf(qty);
                }
            }
        }
    }

    public void emptyCart() {
        productsInCart.clear();
    }

    public double calculateTotalCost() {
        double cost = 0;
        for (Product item : productsInCart) {
            cost += item.calculateCost();
        }
        return cost;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public int getNumberOfProductsInCart() {
        return productsInCart.size();
    }

    public void displayCart() {
        for (Product product : productsInCart) {
            System.out.println(product);
        }
    }

    public String toString() {
        return String.format("Cart: %s", productsInCart);
    }
}
