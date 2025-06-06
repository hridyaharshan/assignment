package threadassignment.Bridge;
public class Person extends Thread {
    private String name;
    private String city;
    private Bridge bridge;

    public Person(String name, String city, Bridge bridge) {
        this.name = name;
        this.city = city;
        this.bridge = bridge;
    }

    public void run() {
        try {
            bridge.cross(name, city);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        // City B people (go first)
        new Person("Alice", "B", bridge).start();
        new Person("Bob", "B", bridge).start();

        // City A people (go after B)
        new Person("Charlie", "A", bridge).start();
        new Person("Diana", "A", bridge).start();
    }
}

