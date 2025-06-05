package javaadvancedassignment;

import java.util.*;
import java.util.stream.Collectors;

public class prefix {

    public static void main(String[] args)
    {
        TreeMap<String,String> input=new TreeMap<>();

        input.put("aju","m");
        input.put("fefe","f");
        input.put("de","m");
        input.put("fcn","m");
        input.put("fceere","f");

        List<String> res=input.entrySet().stream()
                .map( entry ->{

                    String name= entry.getKey();
                    String pre=entry.getValue();

                    if(pre.equals("m")) {
                        return "mr"+name;
                    }
                    else if(pre.equals("f"))
                    {
                        return "mrs"+name;
                    }

                    else
                    {
                        return name;
                    }

                }).collect(Collectors.toList());


        System.out.println(res);
    }

    }

