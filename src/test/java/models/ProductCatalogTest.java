package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sharina Chavez-Lissjanis
 */
class ProductCatalogTest {

    /**
     * Test class for the ProductCatalog class.
     * This class contains unit tests to verify the functionality of the ProductCatalog methods.
     */

    private ProductCatalog productCatalog;


    /**
     * Sets up the test environment before each test case.
     * Resets the nextId for consistent testing and initializes a new ProductCatalog instance.
     */
    @BeforeEach
    void setUp() {
        // Reset the nextId for consistent testing
        Product.resetNextId();
        productCatalog = new ProductCatalog();
    }

    /**
     * Tests the findProductById method for an existing product.
     * Asserts that the found product is not null and has the expected product ID.
     */
    @Test
    void testFindProductById_ExistingProduct() {
        int existingProductId = 1; // The first product should have ID 1

        Product foundProduct = productCatalog.findProductById(existingProductId);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(existingProductId, foundProduct.getProductId());
    }
    
    /**
     * Tests the findProductById method for a non-existing product.
     * Asserts that the found product is null.
     */
    @Test
    void testFindProductById_NonExistingProduct() {
        int nonExistingProductId = 999; // A non-existing ID

        Product foundProduct = productCatalog.findProductById(nonExistingProductId);

        // Assert
        assertNull(foundProduct);
    }

    /**
     * Tests the getHairCareProducts method.
     * Asserts that the returned list of hair care products is not null, not empty, and has the expected size.
     */
    @Test
    void testGetHairCareProducts() {
        List<Product> hairCareProducts = productCatalog.getHairCareProducts();

        // Assert
        assertNotNull(hairCareProducts);
        assertFalse(hairCareProducts.isEmpty());
        assertEquals(6, hairCareProducts.size()); // Assuming there are 6 hair care products
    }

    /**
     * Tests the getSkinCareProducts method.
     * Asserts that the returned list of skin care products is not null, not empty, and has the expected size.
     */
    @Test
    void testGetSkinCareProducts() {
        List<Product> skinCareProducts = productCatalog.getSkinCareProducts();

        // Assert
        assertNotNull(skinCareProducts);
        assertFalse(skinCareProducts.isEmpty());
        assertEquals(6, skinCareProducts.size()); // Assuming there are 6 skin care products
    }

    /**
     * Tests the displayProducts method.
     * Asserts that the method executes without errors when displaying hair care products.
     */
    @Test
    void testDisplayProducts() {
        List<Product> hairCareProducts = productCatalog.getHairCareProducts();

        productCatalog.displayProducts(hairCareProducts);


    }
}