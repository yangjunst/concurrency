package com.wangwenjun.concurrent.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectorExample1
{
    public static void main(String[] args)
    {
        Stream<Production> stream = Stream.of(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );
        //groupByTradition();
        //group(stream);
        partitionByTradition();
        //partitioning(stream);
    }

    private static void partitioning(Stream<Production> stream)
    {
        Map<Boolean, List<Production>> level = stream.collect(Collectors.partitioningBy(p -> p.getPrice() > 100));
        System.out.println(level);
    }

    private static void partitionByTradition()
    {
        List<Production> list = Arrays.asList(new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d));
        final Map<String, List<Production>> prodLevel = new HashMap<>();
        for (Production p : list)
        {
            String level = calculateLevel(p.getPrice());
            prodLevel.computeIfAbsent(level, key -> new ArrayList<>()).add(p);
        }

        System.out.println(prodLevel);
    }

    private static String calculateLevel(double price)
    {
        if (price > 0 && price < 100)
        {
            return "LOW";
        } else if (price >= 100)
        {
            return "HIGH";
        } else
        {
            throw new IllegalArgumentException("Illegal production price.");
        }
    }

    private static void groupByTradition()
    {
        List<Production> list = Arrays.asList(new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d));
        final Map<String, Double> prodPrice = new HashMap<>();
        for (Production p : list)
        {
            String prodName = p.getName();
            double price = p.getPrice();
            if (prodPrice.containsKey(prodName))
            {
                Double totalPrice = prodPrice.get(prodName);
                prodPrice.put(prodName, totalPrice + price);
            } else
            {
                prodPrice.put(prodName, price);
            }
        }

        assert prodPrice.size() == 4;
        assert prodPrice.get("T-Shirt") == 43.34d;
        assert prodPrice.get("cloth") == 99.99d + 199.99d;
        assert prodPrice.get("shoe") == 123.8d + 32.5d;
        assert prodPrice.get("hat") == 26.5d;
    }

    private static void group(Stream<Production> stream)
    {
        Map<String, Double> groupingPrice = stream.collect(
                Collectors.groupingBy(
                        Production::getName,
                        Collectors.summingDouble(Production::getPrice)
                )
        );
        assert groupingPrice.size() == 4;
        assert groupingPrice.get("T-Shirt") == 43.34d;
        assert groupingPrice.get("cloth") == 99.99d + 199.99d;
        assert groupingPrice.get("shoe") == 123.8d + 32.5d;
        assert groupingPrice.get("hat") == 26.5d;
    }

    private static void summingDouble(Stream<Production> stream)
    {
        Double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
                .collect(Collectors.summingDouble(Production::getPrice));
        assert totalPrice == 99.99d + 199.99d;
    }

    private static void doubleStreamSum(Stream<Production> stream)
    {
        Double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
                .mapToDouble(Production::getPrice)
                .sum();
        assert totalPrice == 99.99d + 199.99d;
    }

    private static void doubleStreamReduce(Stream<Production> stream)
    {
        Double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
                .mapToDouble(Production::getPrice)
                .reduce(0, Double::sum);
        assert totalPrice == 99.99d + 199.99d;
    }
}
