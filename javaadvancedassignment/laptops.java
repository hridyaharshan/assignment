package javaadvancedassignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class laptops {
    public static void main(String[] args)
    {
        List<List<String>> input=new ArrayList<>();

        List<String> l1 = new ArrayList<>();
        l1.add("Dell");
        l1.add("Intel i7");
        l1.add("16"); // RAM
        l1.add("4");  // GPU
        l1.add("512"); // HDD
        l1.add("2023-05-20"); // Date in ISO format

        List<String> l2 = new ArrayList<>();
        l2.add("HP");
        l2.add("Intel i5");
        l2.add("8");
        l2.add("2");
        l2.add("256");
        l2.add("2022-03-10");

        List<String> l3 = new ArrayList<>();
        l3.add("Asus");
        l3.add("Intel i7");
        l3.add("32");
        l3.add("8");
        l3.add("1024");
        l3.add("2024-01-15");

        input.add(l1);
        input.add(l2);
        input.add(l3);

        // Example: print the data


        int minRam=16;

        int minGpu=5;


        List<List<String>> res=input.stream()
                .filter(l -> Integer.parseInt(l.get(2)) >= minRam && Integer.parseInt(l.get(3)) >= minGpu)
                .collect(Collectors.toList());

        System.out.println(res);

        Map<String, List<List<String>>>res1=input.stream().collect(Collectors.groupingBy(x->x.get(1)));


        res1.forEach((processor, laptops) -> {
            System.out.println("Processor: " + processor);
            laptops.forEach(System.out::println);
            System.out.println();
        });


    }


}
