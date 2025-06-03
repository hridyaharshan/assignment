package collectionassignment;

import java.util.*;

public class ProductCatalogue {
    private Map<Product, Integer> catalogue = new HashMap<>();

    public void addProduct(Product p, int quantity) {
        if (catalogue.containsKey(p)) {
            System.out.println("Duplicate Product ID. Not added.");
        } else {
            catalogue.put(p, quantity);
            System.out.println("Product added to catalogue.");
        }
    }

    public void updateQuantity(int productId, int quantity) {
        for (Product p : catalogue.keySet()) {
            if (p.getProductId() == productId) {
                catalogue.put(p, quantity);
                System.out.println("Quantity updated.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void deleteProduct(int productId) {
        catalogue.entrySet().removeIf(entry -> entry.getKey().getProductId() == productId);
        System.out.println("Product removed if it existed.");
    }

    public void listCatalogue() {
        for (Map.Entry<Product, Integer> entry : catalogue.entrySet()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }
    }

    public void sortById() {
        catalogue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Product::getProductId)))
                .forEach(entry -> System.out.println(entry.getKey() + " | Qty: " + entry.getValue()));
    }

    public void sortByName() {
        catalogue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Product::getProductName)))
                .forEach(entry -> System.out.println(entry.getKey() + " | Qty: " + entry.getValue()));
    }

    public static void main(String[] args) {
        ProductCatalogue pc = new ProductCatalogue();

        Product p1 = new Product(201, "Laptop", "Computers", 999.99);
        Product p2 = new Product(202, "Monitor", "Computers", 199.99);
        Product p3 = new Product(201, "Duplicate", "Error", 0.0);

        pc.addProduct(p1, 10);
        pc.addProduct(p2, 5);
        pc.addProduct(p3, 1); // Should be ignored due to duplicate ID

        System.out.println("\nCatalogue:");
        pc.listCatalogue();

        pc.updateQuantity(202, 8);
        pc.sortByName();

        pc.deleteProduct(201);
        pc.listCatalogue();
    }
}

