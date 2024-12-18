package models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void setUp() {
        // Reset the nextId before each test to ensure isolation
        Product.resetNextId();
    }

    @Test
    void testProductConstructor() {
        Product product = new Product("Laptop", 999.99, 10);
        assertEquals(1, product.getProductId());
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice(), 0.001);
        assertEquals(10, product.getQuantity());
    }

    @Test
    void testCreateForCart() {
        Product product = new Product("Phone", 599.99, 20);
        Product cartProduct = product.createForCart(2);

        assertEquals(product.getProductId(), cartProduct.getProductId());
        assertEquals(product.getName(), cartProduct.getName());
        assertEquals(product.getPrice(), cartProduct.getPrice(), 0.001);
        assertEquals(2, cartProduct.getQuantity());
    }

    @Test
    void testTakeFromShelf() {
        Product product = new Product("Tablet", 299.99, 15);
        product.takeFromShelf(5);
        assertEquals(10, product.getQuantity());
    }

    @Test
    void testPutBackOnShelf() {
        Product product = new Product("Headphones", 149.99, 5);
        product.putBackOnShelf(3);
        assertEquals(8, product.getQuantity());
    }

    @Test
    void testCalculateCost() {
        Product product = new Product("Keyboard", 49.99, 3);
        assertEquals(149.97, product.calculateCost(), 0.001);
    }

    @Test
    void testSetQuantity() {
        Product product = new Product("Mouse", 29.99, 7);
        product.setQuantity(10);
        assertEquals(10, product.getQuantity());
    }

    @Test
    void testToString() {
        Product product = new Product("Monitor", 199.99, 8);
        assertEquals("ID: 1 Name: Monitor Price: 199.99 Quantity: 8", product.toString());
    }

    @Test
    void testEquals() {
        Product product1 = new Product("Printer", 149.99, 5);
        Product product2 = new Product("Printer", 149.99, 5);
        Product product3 = new Product("Scanner", 99.99, 3);

        assertEquals(product1, product2);
        assertNotEquals(product1, product3);
    }

    @Test
    void testHashCode() {
        Product product1 = new Product("Camera", 299.99, 4);
        Product product2 = new Product("Camera", 299.99, 4);

        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testNextIdIncrement() {
        Product product1 = new Product("Speaker", 79.99, 6);
        Product product2 = new Product("Microphone", 59.99, 9);

        assertEquals(1, product1.getProductId());
        assertEquals(2, product2.getProductId());
    }
}