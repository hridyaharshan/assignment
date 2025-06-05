package javaadvancedassignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Company {
    public static void main(String[] args)
    {
        List<String> d1=new ArrayList<>();

        d1.add("neha");
        d1.add("aju");
        d1.add("suresh");

        List<String> d2=new ArrayList<>();

        d2.add("wedwh");
        d2.add("dwhe");
        d2.add("dweew");


        List<String> d3=new ArrayList<>();

        d3.add("aasad");
        d3.add("ojpo");
        d3.add("bjed");


        List<List<String>>  input=new ArrayList<>();

        input.add(d1);
        input.add(d2);
        input.add(d3);


       //1
        List<String> alldepartmenttogehter=input.stream().flatMap(x->x.stream()).toList();


        System.out.println(alldepartmenttogehter);


        //2

        List<String> starting=alldepartmenttogehter.stream().filter(x->x.startsWith("a")).toList();

        System.out.println(starting);


        //3

        List<String> sorting= new ArrayList<>(alldepartmenttogehter.stream().sorted().toList());

        System.out.println(sorting);


        //4

        Map<Character,List<String>> grouping=sorting.stream().collect(Collectors.groupingBy(x->x.charAt(0)));

        grouping.forEach((key,value) ->
                System.out.println(key + " " + value));



        //5

        Collections.shuffle(sorting);


        AtomicInteger counter = new AtomicInteger(0);

        Map<Integer, List<String>> teams = sorting.stream()
                .collect(Collectors.groupingBy(
                        e -> counter.getAndIncrement() % 5
                ));


        //6


        Map<Integer,List<String>> combingtothree=teams.entrySet().stream()
                .collect(Collectors
                        .groupingBy(
                                e->e.getKey()%3,
                                Collectors
                                        .flatMapping(x->x.getValue().stream(),Collectors.toList()
                                        )
                        ));


        combingtothree.forEach((division, members) ->
                System.out.println("Division " + (division + 1) + ": " + members));











    }
}
