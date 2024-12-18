package models;

import java.util.*;

/**
 * @author Najib Bardash
 *
 * This class creates a shoppingcart object that hos a list of products.
 */
public class ShoppingCart {
    private List<Product> productsInCart;
    // It is initiated with an empty ArrayList
    public ShoppingCart() {
        productsInCart = new ArrayList<>();
    }

    /**
     * This method adds a product to the cart. It does this in a few steps.
     * First it checks for illegal choices of requested quantities.
     *
     * Then it checks if the product already is in the cart, in that case
     * it adds the requested qty on top of the existing. When something is added to the cart,
     * the amount on the shelf(the store) is decreased by the same amount.
     * It creates a copy of the product from the store into the cart, but with the amount
     * that the customers want to buy.
     *
     * @param p Product to be added to cart.
     * @param qty Is the requested amount.
     */
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

    /**
     * This works in a mirrored way from the addProduct. First checks for illegal quantities.
     * Then it makes a distinction of and opportunity to put back a fraction of or the whole
     * amount of a product. If a fraction of the product is removed, a new copy of the product
     * with the updated quantity is created.
     *
     * The amount on the shelf (in store) is updated if something is put back.
     *
     * @param p The product to remove.
     * @param qty The qty to be removed.
     */
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

    /**
     * This method empties the whole cart.
     */
    public void emptyCart() {
        productsInCart.clear();
    }

    /**
     * This method calculates the total cost for the products in the cart by calling the
     * calculateCost-method in Product to get the total cost for each item, and then iterates
     * this process for all items in the cart.
     *
     * @return the total cost for the products in the cart.
     */
    public double calculateTotalCost() {
        double cost = 0;
        for (Product item : productsInCart) {
            cost += item.calculateCost();
        }
        return cost;
    }

    /**
     *
     * @return the list of products in the cart.
     */
    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    /**
     *
     * @return the number of products in the cart.
     */
    public int getNumberOfProductsInCart() {
        return productsInCart.size();
    }

    /**
     * Prints the info of the products in the cart.
     */
    public void displayCart() {
        for (Product product : productsInCart) {
            System.out.println(product);
        }
    }

    /**
     *
     * @return a formatted view of the products in the cart.
     */
    public String toString() {
        return String.format("Cart: %s", productsInCart);
    }
}
