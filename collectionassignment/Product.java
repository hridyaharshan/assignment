package collectionassignment;

import java.util.Objects;

public class Product {
    int id;
    String name;
    String category;
    double price;

    public Product(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name +
                ", Category: " + category + ", Price: " + price);
    }
}

