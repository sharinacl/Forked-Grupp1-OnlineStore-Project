package models;

import org.junit.jupiter.api.Test;
import utilities.CustomerGender;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer testCustomer;
    private Map<Integer, List<Product>> orders;
    private List<Product> cart = new ArrayList<>();

    public CustomerTest() {
        testCustomer = new Customer("Dave", "dave.johnson@gmail.com", "MALE", new ShoppingCart());
        orders = new HashMap<>();
    }

    @Test
    void validCustomer() {
        assertEquals("Dave", testCustomer.getName());
        assertEquals("dave.johnson@gmail.com", testCustomer.getEmail());
        assertEquals(CustomerGender.MALE, testCustomer.getCustomerGender());
    }

    @Test
    void notValidCustomerName() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "lily.dawn@gmail.com", "FEMALE", new ShoppingCart()));
    }

    @Test
    void notValidCustomerEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Lily", "lily.dawngmail.com", "FEMALE", new ShoppingCart()));
    }

    @Test
    void notValidCustomerGender() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Lily", "lily.dawn@gmail.com", "feamle", new ShoppingCart()));
    }

    @Test
    void uniqueCustomerID() {
        Customer c1 = new Customer("Lily", "lily.dawn@gmail.com", "FEMALE", new ShoppingCart());
        Customer c2 = new Customer("Hubert", "hubbe@gmail.com", "MALE", new ShoppingCart());
        assertNotEquals(c1.getCustomerID(), c2.getCustomerID());
    }

    @Test
    void findOrderHistory() {
        Product shampoo = new Product("Shampoo", 55, 10);
        cart.add(shampoo);
        Map<Integer, List<Product>> order = new HashMap<>();
        order.put(1, cart);
        assertEquals(1, order.size());
    }

    @Test
    void emptyOrderHistoryFound() {
        Map<Integer, List<Product>> order = new HashMap<>();
        assertEquals(0, order.size());
    }

    @Test
    void placeOrderTest() {
        Product shampoo = new Product("Shampoo", 55, 10);
        Product conditioner = new Product("Conditioner", 52, 8);
        int orderID = 1;
        cart = List.of(shampoo, conditioner);
        orders.put(orderID, cart);
        assertEquals(1, orders.size());
    }

    @Test
    void emptyCartTest() {
        Product shampoo = new Product("Shampoo", 55, 10);
        int orderID = 1;
        cart.add(shampoo);
        cart.clear();
        assertEquals(0, cart.size());
    }
}
