package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Najib Bardash
 */
class ShoppingCartTest {

    private ShoppingCart cart;
    private Product pepsi;

    public ShoppingCartTest() {
        cart = new ShoppingCart();
        pepsi = new Product("Pepsi", 8.50, 10);
        cart.addProductToCart(pepsi, 2);
    }

    @Test
    void testAddProductToCart() {
        Product test = new Product("Test", 5.00, 1);
        cart.addProductToCart(test, 1); // getNumberOfProductsInCart should be 1 BEFORE adding this item (one product already in cart).
        assertEquals(2, cart.getNumberOfProductsInCart()); // Now the value should be 2 because we have added a second product.
        assertEquals(cart.getProductsInCart().get(1), test); // This tests if the actual product is in the array.
    }

    @Test
    void testAddProductToCartWithMoreQuantityThanIsAvailable() {
        assertThrows(IllegalArgumentException.class, () -> cart.addProductToCart(pepsi, 9));
    } // This will throw an exception because we only have 8 left in stock

    @Test
    void testRemoveQuantityOfProductFromCart() {
        cart.removeProductFromCart(pepsi, 1); // We had this product with a quantity of 2 before.
        assertEquals(1, cart.getProductsInCart().get(0).getQuantity()); // Now we should only have one.
    }

    @Test
    void testRemoveProductFromCart() {
        cart.removeProductFromCart(pepsi, 1); // We had this product with a quantity of 2 before.
        assertEquals(1, cart.getProductsInCart().get(0).getQuantity()); // Now we should only have one.
    }

    @Test
    void testEmptyCart() {
        Product test = new Product("Test", 5.00, 10);
        cart.addProductToCart(test, 10); // Now we have two products in the cart.
        cart.emptyCart();
        assertEquals(0, cart.getNumberOfProductsInCart()); // Now it should be empty
    }

    @Test
    void testCalculateTotalCost() {
        Product test = new Product("Test", 5.00, 10);
        cart.addProductToCart(test, 10); // Now we have two products in the cart.
        assertEquals(67, cart.calculateTotalCost()); // Total worth is each item * price * qty
    }

    @Test
    void testGetNumberOfProductsInCart() {
        assertTrue(cart.getNumberOfProductsInCart() == 1); // We only have one item in cart.
    }
}