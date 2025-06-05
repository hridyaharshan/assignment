package javaadvancedassignment;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class frequency {
    public static void main(String[] args)
    {
        String str="aaabbbbccdd";


        Map<String,Long> res= Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));


        System.out.println(res);
    }
}
