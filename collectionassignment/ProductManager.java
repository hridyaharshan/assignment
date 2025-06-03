package collectionassignment;

import java.util.*;

public class ProductManager {
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product p) {
        if (products.add(p)) {
            System.out.println("Product added.");
        } else {
            System.out.println("Duplicate Product ID. Not added.");
        }
    }

    public void updateProduct(int id, String name, String category, double price) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                p.setProductName(name);
                p.setCategory(category);
                p.setPrice(price);
                System.out.println("Product updated.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void deleteProduct(int id) {
        products.removeIf(p -> p.getProductId() == id);
        System.out.println("Product deleted if it existed.");
    }

    public void getProduct(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void listAll() {
        products.forEach(System.out::println);
    }

    public void sortById() {
        products.stream()
                .sorted(Comparator.comparingInt(Product::getProductId))
                .forEach(System.out::println);
    }

    public void sortByName() {
        products.stream()
                .sorted(Comparator.comparing(Product::getProductName))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        ProductManager pm = new ProductManager();
        pm.addProduct(new Product(101, "Keyboard", "Electronics", 35.5));
        pm.addProduct(new Product(102, "Mouse", "Electronics", 25.0));
        pm.addProduct(new Product(101, "Duplicate", "Error", 0.0)); // Should be ignored

        System.out.println("\nAll Products:");
        pm.listAll();

        System.out.println("\nSorted by Name:");
        pm.sortByName();

        pm.updateProduct(102, "Wireless Mouse", "Electronics", 30.0);
        pm.getProduct(102);

        pm.deleteProduct(101);
        pm.listAll();
    }
}

