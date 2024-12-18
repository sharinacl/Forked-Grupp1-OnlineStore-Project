package models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sharina Chavez-Lissjanis
 * Represents a catalog of products, categorized into hair care and skin care products.
 * Theclass provides methods to manage and retrieve products from the catalog.
 */
public class ProductCatalog {
    private List<Product> hairCareProducts = new ArrayList<>();
    private List<Product> skinCareProducts = new ArrayList<>();

    /**
     * Initializes the product catalog.
     * Each product is added with a name, price, and initial quantity.
     */
    public ProductCatalog() {
        // hairCare products
        hairCareProducts.add(new Product("Shampoo1", 8.0, 3));
        hairCareProducts.add(new Product("Shampoo2", 12.0, 10));
        hairCareProducts.add(new Product("Styling Gel1", 15.0, 5));
        hairCareProducts.add(new Product("Styling Gel2", 20.0, 12));
        hairCareProducts.add(new Product("Conditioner1", 10.0, 10));
        hairCareProducts.add(new Product("Conditioner2", 12.0, 25));

        // skinCare products
        skinCareProducts.add(new Product("Shower Gel1", 5.0, 20));
        skinCareProducts.add(new Product("Shower Gel2", 7.0, 12));
        skinCareProducts.add(new Product("Day Cream1", 20.0, 10));
        skinCareProducts.add(new Product("Day Cream2", 25.0, 10));
        skinCareProducts.add(new Product("Body Lotion1", 15.0, 20));
        skinCareProducts.add(new Product("Body Lotion2", 20.0, 15));
    }

    /**
     * Finds a product in the catalog by its unique ID.
     * The method searches through both hair care and skin care product lists.
     *
     * @param id The unique ID of the product to search for.
     * @return The Product object if found, or null if no product matches the given ID.
     */
    public Product findProductById(int id) {
        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(hairCareProducts);
        allProducts.addAll(skinCareProducts);

        for (Product product : allProducts) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null; // Return null if no product is found
    }

    /**
     * Retrieves the list of hair care products in the catalog.
     *
     * @return A list of hair care products.
     */
    public List<Product> getHairCareProducts() {
        return hairCareProducts;
    }

    /**
     * Retrieves the list of skin care products in the catalog.
     *
     * @return A list of skin care products.
     */
    public List<Product> getSkinCareProducts() {
        return skinCareProducts;
    }

    /**
     * Displays the details of all products in the provided list.
     * This method prints each product's information to the console.
     *
     * @param products The list of products to display.
     */
    // Added method to display products
    public void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}