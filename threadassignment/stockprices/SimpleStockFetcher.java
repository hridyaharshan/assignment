package threadassignment.stockprices;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SimpleStockFetcher {

    public static void main(String[] args) throws Exception {

        Map<String, Double> stockPrices = loadCSV("C:\\Users\\HridyaHarshan\\IdeaProjects\\assignment\\src\\threadassignment\\stockprices\\stock.csv");//loading them


        List<String> symbolss = Arrays.asList("AAPL", "GOOGL", "MSFT");


        ExecutorService executor = Executors.newFixedThreadPool(3);//the thread pool present


        Map<String, Double> result = new ConcurrentHashMap<>();

        for (String s : symbolss) {
            executor.submit(() -> {
                if (stockPrices.containsKey(s)) {
                    Double res=stockPrices.get(s);
                    result.put(s, res);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);


        System.out.println("Fetched Stock Prices:");
        result.forEach((s, price) -> System.out.println(s + " : " + price));
    }


    public static Map<String, Double> loadCSV(String fileName) throws IOException {
        Map<String, Double> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        br.readLine(); // skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                map.put(parts[0].trim(), Double.parseDouble(parts[1].trim()));
            }
        }
        return map;
    }
}
