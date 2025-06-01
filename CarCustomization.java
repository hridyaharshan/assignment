package javaassignment;

import java.util.Scanner;

// Class to store car details
class Car {
    String manufacturer;
    String model;
    String transmission;
    String fuelType;
    String color;
    String location;

    void display() {
        System.out.println(" Customization ");
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("Transmission: " + transmission);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Color: " + color);
        System.out.println("Location: " + location);
    }
}

// Class to handle model selection for Mahindra
class ModelMenu {
    public static String selectModel(Scanner scanner) {
        System.out.println("\nSelect Mahindra Model:");
        System.out.println("1. Scorpio");
        System.out.println("2. Thar");
        System.out.println("3. Scorpio N");
        System.out.println("4. XUV 700");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (choice) {
            case 1: return "Scorpio";
            case 2: return "Thar";
            case 3: return "Scorpio N";
            case 4: return "XUV 700";
            default: return "Unknown Model";
        }
    }
}

// Class to handle all menus
class Menu {
    Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selectManufacturer() {
        System.out.println("Select Manufacturer:");
        System.out.println("1. Mahindra");
        System.out.println("2. Tata");
        System.out.println("3. Maruti");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return "Mahindra";
            case 2: return "Tata";
            case 3: return "Maruti";
            default: return "Unknown";
        }
    }

    public String selectTransmission() {
        System.out.println("\nSelect Transmission:");
        System.out.println("1. Manual");
        System.out.println("2. Automatic");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return (choice == 1) ? "Manual" : "Automatic";
    }

    public String selectFuelType() {
        System.out.println("\nSelect Fuel Type:");
        System.out.println("1. Diesel");
        System.out.println("2. Petrol");
        System.out.println("3. CNG");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return "Diesel";
            case 2: return "Petrol";
            case 3: return "CNG";
            default: return "Unknown";
        }
    }

    public String selectColor() {
        System.out.println("\nSelect Color:");
        System.out.println("1. Silver");
        System.out.println("2. Blue");
        System.out.println("3. Yellow");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return "Silver";
            case 2: return "Blue";
            case 3: return "Yellow";
            default: return "Unknown";
        }
    }

    public String selectLocation() {
        System.out.println("\nSelect Location:");
        System.out.println("1. Delhi");
        System.out.println("2. Bangalore");
        System.out.println("3. Hyderabad");
        System.out.println("4. Chennai");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return "Delhi";
            case 2: return "Bangalore";
            case 3: return "Hyderabad";
            case 4: return "Chennai";
            default: return "Unknown";
        }
    }
}

// Main class
public class CarCustomization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        Menu menu = new Menu(scanner);

        System.out.println("Car Customization");

        car.manufacturer = menu.selectManufacturer();

        if (car.manufacturer.equalsIgnoreCase("Mahindra")) {
            car.model = ModelMenu.selectModel(scanner);
        } else {
            System.out.print("\nEnter model name for " + car.manufacturer + ": ");
            car.model = scanner.nextLine();
        }

        car.transmission = menu.selectTransmission();
        car.fuelType = menu.selectFuelType();
        car.color = menu.selectColor();
        car.location = menu.selectLocation();


        car.display();

        scanner.close();
    }
}
