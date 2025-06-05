package collectionassignment;

import java.util.*;

public class Productcata {

    // Product class (entity)
    static class Product {
        private final int productId;
        private final String productName;
        private final String category;
        private final double price;

        public Product(int productId, String productName, String category, double price) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
            this.price = price;
        }

        public int getProductId() { return productId; }
        public String getProductName() { return productName; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return productId == product.productId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(productId);
        }

        @Override
        public String toString() {
            return String.format("ID: %d, Name: %s, Category: %s, Price: %.2f",
                    productId, productName, category, price);
        }
    }

    // ProductCatalog class (manager)
    static class ProductCatalog {
        private final Map<Product, Integer> catalog = new HashMap<>();

        // Add product
        public boolean addProduct(Product product, int quantity) {
            if (catalog.containsKey(product)) {
                System.out.println(" Product already exists!");
                return false;
            }
            catalog.put(product, quantity);
            System.out.println("Product added: " + product.getProductName());
            return true;
        }

        // Retrieve quantity
        public Integer getQuantity(Product product) {
            return catalog.get(product);
        }

        // Update quantity
        public boolean updateQuantity(Product product, int newQuantity) {
            if (!catalog.containsKey(product)) {
                System.out.println("Product not found!");
                return false;
            }
            catalog.put(product, newQuantity);
            System.out.println("Quantity updated for: " + product.getProductName());
            return true;
        }

        // Delete product
        public boolean deleteProduct(Product product) {
            if (catalog.remove(product) != null) {
                System.out.println("Product removed: " + product.getProductName());
                return true;
            } else {
                System.out.println("Product not found to delete.");
                return false;
            }
        }

        // Sort by ID
        public List<Map.Entry<Product, Integer>> sortById() {
            List<Map.Entry<Product, Integer>> sorted = new ArrayList<>(catalog.entrySet());
            sorted.sort(Comparator.comparing(e -> e.getKey().getProductId()));
            return sorted;
        }

        // Sort by Name
        public List<Map.Entry<Product, Integer>> sortByName() {
            List<Map.Entry<Product, Integer>> sorted = new ArrayList<>(catalog.entrySet());
            sorted.sort(Comparator.comparing(e -> e.getKey().getProductName()));
            return sorted;
        }

        // Display entire catalog
        public void displayCatalog() {
            System.out.println("\n Product Catalog:");
            for (Map.Entry<Product, Integer> entry : catalog.entrySet()) {
                System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        Product p1 = new Product(101, "Laptop", "Electronics", 999.99);
        Product p2 = new Product(102, "Phone", "Electronics", 499.49);
        Product p3 = new Product(103, "Desk", "Furniture", 150.00);

        catalog.addProduct(p1, 10);
        catalog.addProduct(p2, 5);
        catalog.addProduct(p3, 7);
        catalog.addProduct(p1, 3); // Try duplicate

        catalog.displayCatalog();

        System.out.println("\n Sorted by Name:");
        for (Map.Entry<Product, Integer> entry : catalog.sortByName()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }

        catalog.updateQuantity(p2, 8);
        catalog.deleteProduct(p1);

        catalog.displayCatalog();
    }
}

