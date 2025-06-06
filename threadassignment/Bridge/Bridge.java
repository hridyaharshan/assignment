package threadassignment.Bridge;

public class Bridge {
    private boolean tokenInCityB = true;  // token starts at City B
    private boolean bridgeOccupied = false;
    private int cityBRemaining = 2;        // number of City B people expected

    public synchronized void cross(String name, String city) throws InterruptedException {
        // Wait until bridge free and token is at your city (and City B people go first)
        while (bridgeOccupied || !canCross(city)) {
            wait();
        }

        // Occupy the bridge
        bridgeOccupied = true;
        System.out.println(name + " from City " + city + " is crossing the bridge.");
        Thread.sleep(1000);  // simulate crossing time
        System.out.println(name + " has crossed the bridge.");

        // After crossing, update token and counts
        if (city.equals("B")) {
            cityBRemaining--;
            tokenInCityB = false;  // token moves to City A side after B crosses
        } else {
            tokenInCityB = true;   // token moves back to City B side after A crosses
        }

        bridgeOccupied = false;
        notifyAll();  // wake up waiting threads
    }

    private boolean canCross(String city) {
        if (city.equals("B")) {
            return tokenInCityB;
        } else {
            return !tokenInCityB && cityBRemaining == 0;
        }
    }
}

