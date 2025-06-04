package collectionassignment;

import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Product Management Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Sort by Product ID");
            System.out.println("6. Sort by Product Name");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    boolean exists = false;

                    // Check for duplicate ID
                    for (Product p : products) {
                        if (p.id == id) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Product ID already exists!");
                    } else {
                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();

                        products.add(new Product(id, name, category, price));
                        System.out.println("Product added successfully.");
                    }
                }

                case 2 -> {
                    if (products.isEmpty()) {
                        System.out.println("No products to display.");
                    } else {
                        System.out.println("--- Product List ---");
                        for (Product p : products) {
                            p.display();
                        }
                    }
                }

                case 3 -> {
                    System.out.print("Enter Product ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    boolean found = false;

                    for (Product p : products) {
                        if (p.id == id) {
                            System.out.print("Enter new name: ");
                            p.name = sc.nextLine();
                            System.out.print("Enter new category: ");
                            p.category = sc.nextLine();
                            System.out.print("Enter new price: ");
                            p.price = sc.nextDouble();
                            System.out.println("Product updated.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Product not found.");
                    }
                }

                case 4 -> {
                    System.out.print("Enter Product ID to delete: ");
                    int id = sc.nextInt();
                    boolean removed = false;

                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).id == id) {
                            products.remove(i);
                            System.out.println("Product deleted.");
                            removed = true;
                            break;
                        }
                    }

                    if (!removed) {
                        System.out.println("Product not found.");
                    }
                }

                case 5 -> {
                    // Sort by ID (simple bubble sort)
                    for (int i = 0; i < products.size(); i++) {
                        for (int j = i + 1; j < products.size(); j++) {
                            if (products.get(i).id > products.get(j).id) {
                                Product temp = products.get(i);
                                products.set(i, products.get(j));
                                products.set(j, temp);
                            }
                        }
                    }
                    System.out.println("Products sorted by ID.");
                }

                case 6 -> {
                    // Sort by Name
                    for (int i = 0; i < products.size(); i++) {
                        for (int j = i + 1; j < products.size(); j++) {
                            if (products.get(i).name.compareTo(products.get(j).name) > 0) {
                                Product temp = products.get(i);
                                products.set(i, products.get(j));
                                products.set(j, temp);
                            }
                        }
                    }
                    System.out.println("Products sorted by Name.");
                }

                case 7 -> {
                    System.out.println("Exiting program.");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}


