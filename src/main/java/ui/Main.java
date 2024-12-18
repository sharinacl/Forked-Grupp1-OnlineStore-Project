package ui;

import models.Customer;
import models.Product;
import models.ProductCatalog;
import models.ShoppingCart;

import java.util.*;

/**
 * @author Jessica, Najib, Sharina
 *
 * This app is similiar to an online store where you enter as a customer and get to enter your
 * information. Then a Customer object is created who can browse around the store, make purchases
 * and view order history.
 *
 */
public class Main {
    private static final ProductCatalog catalog = new ProductCatalog();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Customer newCustomer;
            while (true) {
                try {
                    System.out.println("Welcome to the Shopping Application!");
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine().trim();
                    System.out.println("Enter your gender MALE/FEMALE/OTHER: ");
                    String gender = scanner.nextLine().trim().toUpperCase();

                    newCustomer = new Customer(name, email, gender, new ShoppingCart());
                    break;
                } catch (IllegalArgumentException ex) {
                    System.out.println("Wrong input: " + ex.getMessage());
                }
            }

            running: while (true) {
                displayMainMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        viewAllProducts(scanner, newCustomer);
                        break;
                    case 2:
                        viewCategories(scanner, newCustomer);
                        break;
                    case 3:
                        newCustomer.getCart().displayCart();
                        break;
                    case 4:
                        newCustomer.viewOrderHistory();
                        break;
                    case 5:
                        System.out.println("Thank you for using the application. Goodbye!");
                        break running;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method displays the options ath hand for the customer.
     */
    private static void displayMainMenu() {
        System.out.println("\n" + "Main Menu:");
        System.out.println("1. View All Products");
        System.out.println("2. View Product Categories");
        System.out.println("3. View Cart");
        System.out.println("4. View Order History");
        System.out.println("5. Quit");
        System.out.println();
    }

    /**
     * Shows a view of all products in the store
     * @param scanner gives the customer a choice to pick something
     * @param customer is the customer that was created
     */
    private static void viewAllProducts(Scanner scanner, Customer customer) {
        System.out.println("\n" + "All Products:");
        catalog.displayProducts(catalog.getHairCareProducts());
        catalog.displayProducts(catalog.getSkinCareProducts());
        selectProduct(scanner, customer);
    }

    /**
     * Gives the option of seeing products by category or to go back to main menu
     *
     * @param scanner gives the customer a choice to pick something
     * @param customer is the customer that was created
     */
    private static void viewCategories(Scanner scanner, Customer customer) {
        System.out.println("\n" + "Select a category:");
        System.out.println("1. Hair Care");
        System.out.println("2. Skin Care");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                displayCategoryProducts(catalog.getHairCareProducts(), scanner, customer);
                break;
            case 2:
                displayCategoryProducts(catalog.getSkinCareProducts(), scanner, customer);
                break;
            case 3:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        System.out.println();
    }

    /**
     * Shows a view of all products by category.
     *
     * @param products the category of choice.
     * @param scanner gives the customer a choice to pick something.
     * @param customer is the user.
     */
    private static void displayCategoryProducts(List<Product> products, Scanner scanner, Customer customer) {
        System.out.println();
        System.out.println("Products in category:");
        catalog.displayProducts(products);
        selectProduct(scanner, customer);
    }

    /**
     * A product selection menu that is linked to the productID. Checks for illegal choices of
     * quantity.
     *
     * @param scanner gives the customer a choice to pick something.
     * @param customer is the user.
     */
    private static void selectProduct(Scanner scanner, Customer customer) {
        System.out.print("\n" + "Enter the ID of the product you want to view (or 0 to go back): ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (productId == 0) {
            return; // Go back to previous menu
        }
        System.out.println();
        Product selectedProduct = catalog.findProductById(productId);
        if (selectedProduct != null) {
            System.out.println("Product Details:");
            System.out.println(selectedProduct.getName() + "    Quantity Available: " + selectedProduct.getQuantity());
            System.out.println();
            System.out.println("1. Add to Cart");
            System.out.println("2. Back to Previous Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addToCart(selectedProduct, scanner, customer);
                    break;
                case 2:
                    // Go back to previous menu
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Lets the customer add the product to the cart. Checks for illegal options of quantity.
     * Then back to an option menu.
     *
     * @param product is the requested product.
     * @param scanner gives the user a way to choose an option.
     * @param newCustomer is the user.
     */
    private static void addToCart(Product product, Scanner scanner, Customer newCustomer) {
        System.out.println();
        System.out.print("Enter the quantity to add to cart: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }
        if (quantity > product.getQuantity()) {
            System.out.println("Not enough stock available.");
            return;
        }
        newCustomer.getCart().addProductToCart(product, quantity);
        System.out.println();
        System.out.println("Product added to cart.");
        System.out.println("Remaining Quantity: " + product.getQuantity());
        System.out.println();
        System.out.println("1. Add another item");
        System.out.println("2. Go back to Main Menu");
        System.out.println("3. View Cart");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                viewAllProducts(scanner, newCustomer);
                break;
            case 2:
                displayMainMenu();
                break;
            case 3:
                viewCart(scanner, newCustomer);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * A view of the cart and an option-menu following.
     * @param scanner gives the user an option to choose with.
     * @param newCustomer is the user.
     */
    private static void viewCart(Scanner scanner, Customer newCustomer) {
        System.out.println("\n" + "Your Cart:");
        newCustomer.getCart().displayCart();
        System.out.println("Total: $" + newCustomer.getCart().calculateTotalCost() + "\n");
        System.out.println("1. Checkout");
        System.out.println("2. Remove Item from Cart");
        System.out.println("3. Go back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.println("\n" + "Thank you for your purchase!");
                newCustomer.placeOrder();
                displayMainMenu();
                break;
            case 2:
                removeItemFromCart(scanner, newCustomer);
                break;
            case 3:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Removes a product from the cart by getting the id and then calling the remove-method
     * from ShoppingCart.
     *
     * @param scanner gives the user an option to choose.
     * @param newCustomer is the user.
     */
    private static void removeItemFromCart(Scanner scanner, Customer newCustomer) {
        System.out.println("\n" + "Your Cart:");
        newCustomer.getCart().displayCart();
        System.out.print("Enter the ID of the product to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("How many would you like to remove?");
        int removeAmount = scanner.nextInt();
        Product product = catalog.findProductById(productId);
        if (product != null) {
            newCustomer.getCart().removeProductFromCart(product, removeAmount);
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }
}