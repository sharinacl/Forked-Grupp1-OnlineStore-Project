
package models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sharina Chavez-Lissjanis
 *
 *  Test class for the Product class. This class contains unit tests to verify the functionality
 *  * of the Product class, including its constructors, methods, and behavior
 */
class ProductTest {

    /**
     * Resets the static nextId counter before each test to ensure that each test case
     * starts with a fresh product ID sequence.
     */
    @BeforeEach
    void setUp() {
        // Reset the nextId before each test to ensure isolation
        Product.resetNextId();
    }

    /**
     * Tests the Product constructor to ensure that it correctly initializes a product
     * with the given name, price, and quantity, and assigns a unique product ID.
     */
    @Test
    void testProductConstructor() {
        Product product = new Product("Laptop", 999.99, 10);
        assertEquals(1, product.getProductId());
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice(), 0.001);
        assertEquals(10, product.getQuantity());
    }

    /**
     * Tests the createForCart method to ensure that it correctly creates a new Product
     * instance for the shopping cart with the specified quantity.
     */
    @Test
    void testCreateForCart() {
        Product product = new Product("Phone", 599.99, 20);
        Product cartProduct = product.createForCart(2);

        assertEquals(product.getProductId(), cartProduct.getProductId());
        assertEquals(product.getName(), cartProduct.getName());
        assertEquals(product.getPrice(), cartProduct.getPrice(), 0.001);
        assertEquals(2, cartProduct.getQuantity());
    }

    /**
     * Tests the takeFromShelf method to ensure that it correctly reduces the quantity
     * of the product on the shelf.
     */
    @Test
    void testTakeFromShelf() {
        Product product = new Product("Tablet", 299.99, 15);
        product.takeFromShelf(5);
        assertEquals(10, product.getQuantity());
    }

    /**
     * Tests the putBackOnShelf method to ensure that it correctly increases the quantity
     * of the product on the shelf.
     */
    @Test
    void testPutBackOnShelf() {
        Product product = new Product("Headphones", 149.99, 5);
        product.putBackOnShelf(3);
        assertEquals(8, product.getQuantity());
    }

    /**
     * Tests the calculateCost method to ensure that it correctly calculates the total
     * cost of the product based on its price and quantity.
     */
    @Test
    void testCalculateCost() {
        Product product = new Product("Keyboard", 49.99, 3);
        assertEquals(149.97, product.calculateCost(), 0.001);
    }


    /**
     * Tests the setQuantity method to ensure that it correctly sets the quantity of the product.
     */
    @Test
    void testSetQuantity() {
        Product product = new Product("Mouse", 29.99, 7);
        product.setQuantity(10);
        assertEquals(10, product.getQuantity());
    }

    /**
     * Tests the increment of the nextId counter to ensure that each new product gets
     * a unique ID.
     */
    @Test
    void testNextIdIncrement() {
        Product product1 = new Product("Speaker", 79.99, 6);
        Product product2 = new Product("Microphone", 59.99, 9);

        assertEquals(1, product1.getProductId());
        assertEquals(2, product2.getProductId());
    }

}