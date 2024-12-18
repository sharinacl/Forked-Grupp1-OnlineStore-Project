package ui;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> hairCareProducts = new ArrayList<>();
    private List<Product> skinCareProducts = new ArrayList<>();

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

    public List<Product> getHairCareProducts() {
        return hairCareProducts;
    }

    public List<Product> getSkinCareProducts() {
        return skinCareProducts;
    }

    // Added method to display products
    public void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}