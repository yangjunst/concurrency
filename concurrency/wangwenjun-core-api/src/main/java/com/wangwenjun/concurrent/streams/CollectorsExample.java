package com.wangwenjun.concurrent.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsExample
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
        //averagingLong(stream);
        //averagingDouble(stream);
        //collectingAndThen(stream);
        //assert stream.count()==6;
        //assert stream.collect(Collectors.counting())==6;
        //mapping(stream);

//        minBy(stream);
//        summarizingInt(stream);
        //toMap();
//        reducing3(stream);

        Double result1 = IntStream.range(1, 1000).boxed().collect(Collectors.averagingInt(i -> i));
        System.out.println(result1);

        Double result2 = IntStream.range(1, 1000).boxed()
                .parallel().collect(Collectors.averagingInt(i -> i));
        System.out.println(result2);
    }

    private static void reducing1(Stream<Production> stream)
    {
//        Optional<Production> opt = stream.collect(
//                Collectors.reducing(
//                        (p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2)
//        );

        Optional<Production> opt = stream.collect(Collectors.reducing(
                BinaryOperator.maxBy(Comparator.comparingDouble(Production::getPrice)))
        );
        opt.ifPresent(System.out::println);
    }

    private static void reducing2(Stream<Production> stream)
    {
        //stream = Stream.empty();
        Production book = stream.collect(
                Collectors.reducing(new Production("Book", 279.9),
                        (p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2)
        );
        System.out.println(book);
    }

    private static void reducing3(Stream<Production> stream)
    {
        Comparator<Double> comparing = Comparator.comparing(Double::doubleValue);
        Double highestPrice = stream.collect(
                Collectors.reducing(0.0D, Production::getPrice,
                        BinaryOperator.maxBy(comparing)));
        System.out.println(highestPrice);
    }

    private static void partitioningBy1(Stream<Production> stream)
    {
        Map<Boolean, List<Production>> result = stream.collect(Collectors.partitioningBy(p -> p.getPrice() > 100));
        System.out.println(result);
    }

    private static void groupingBy1(Stream<Production> stream)
    {
        Map<String, List<Production>> result = stream.collect(
                Collectors.groupingBy(Production::getName)
        );
        System.out.println(result);
    }

    private static void groupingBy2(Stream<Production> stream)
    {
        Map<String, Set<Production>> result = stream.collect(
                Collectors.groupingBy(Production::getName
                        , Collectors.toSet())
        );
        System.out.println(result);
    }

    private static void groupingBy3(Stream<Production> stream)
    {
        Map<String, Set<Production>> result = stream.collect(
                Collectors.groupingBy(Production::getName, TreeMap::new
                        , Collectors.toSet())
        );
        System.out.println(result);
    }

    private static void partitioningBy2(Stream<Production> stream)
    {
//        Map<Boolean, Set<Production>> result = stream.collect(
//                Collectors.partitioningBy(p -> p.getPrice() > 100, Collectors.toSet())
//        );

//        Map<Boolean, Double> result = stream.collect(
//                Collectors.partitioningBy(
//                        p -> p.getPrice() > 100,
//                        Collectors.summingDouble(Production::getPrice)
//                )
//        );

        Map<Boolean, Double> result = stream.collect(
                Collectors.partitioningBy(
                        p -> p.getPrice() > 100,
                        Collectors.averagingDouble(Production::getPrice)
                )
        );
        System.out.println(result);
    }

    private static void toSet(Stream<Production> stream)
    {
        Set<String> set = stream.map(Production::getName).collect(Collectors.toSet());
        System.out.println(set);
    }

    private static void toList(Stream<Production> stream)
    {
        List<String> list = stream.map(Production::getName).collect(Collectors.toList());
        System.out.println(list);
    }

    private static void toMap()
    {
        Stream<String[]> stream = Stream
                .of(new String[][]{{"Java", "Java Programming"},
                        {"C", "C Programming"},
                        {"Scala", "Scala Programming"}});
        Map<String, String> result = stream.collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(result);
    }

    private static void toMap2(Stream<Production> stream)
    {
        Map<String, List<Production>> result = stream.collect(
                Collectors.toMap(
                        Production::getName, Arrays::asList,
                        (productions, productions2) ->
                        {
                            List<Production> mergeResult = new ArrayList<>();
                            mergeResult.addAll(productions);
                            mergeResult.addAll(productions2);
                            return mergeResult;
                        }

                ));

        System.out.println(result);
    }

    private static void toMap3(Stream<Production> stream)
    {
        TreeMap<String, List<Production>> result = stream.collect(
                Collectors.toMap(
                        Production::getName, Arrays::asList,
                        (productions, productions2) ->
                        {
                            List<Production> mergeResult = new ArrayList<>();
                            mergeResult.addAll(productions);
                            mergeResult.addAll(productions2);
                            return mergeResult;
                        }
                        , TreeMap::new));

        System.out.println(result);
    }

    private static void summarizingInt(Stream<Production> stream)
    {
        IntSummaryStatistics stat = stream.collect(
                Collectors.summarizingInt(p -> (int) p.getPrice())
        );
        System.out.println(stat);
    }

    private static void summarizingDouble(Stream<Production> stream)
    {
        DoubleSummaryStatistics stat = stream.collect(
                Collectors.summarizingDouble(Production::getPrice)
        );
        System.out.println(stat);
    }

    private static void summarizingLong(Stream<Production> stream)
    {
        LongSummaryStatistics stat = stream.collect(
                Collectors.summarizingLong(p -> (long) p.getPrice())
        );
        System.out.println(stat);
    }

    private static void maxBy(Stream<Production> stream)
    {
        Optional<Production> opt = stream.collect(Collectors.maxBy((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));
        opt.ifPresent(p -> System.out.println(p.getName()));
    }

    private static void minBy(Stream<Production> stream)
    {
        Optional<Production> opt = stream.collect(Collectors.minBy((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));
        opt.ifPresent(p -> System.out.println(p.getName()));
    }

    private static void summingInt(Stream<Production> stream)
    {
        Integer result = stream.collect(
                Collectors.summingInt(p -> (int) p.getPrice())
        );
        System.out.println(result);
    }

    private static void summingDouble(Stream<Production> stream)
    {
        double result = stream.collect(
                Collectors.summingDouble(p -> p.getPrice())
        );
        System.out.println(result);
    }

    private static void summingLong(Stream<Production> stream)
    {

        long result = stream.collect(
                Collectors.summingLong(p -> (long) p.getPrice())
        );
        System.out.println(result);
    }

    private static void mapping(Stream<Production> stream)
    {
        double deductInComing = stream.collect(
                Collectors.mapping(
                        p -> p.getPrice() * 0.1, Collectors.summingDouble(Double::doubleValue)
                )
        );

        //deductInComing=stream.map(p->p.getPrice()*0.1).mapToDouble(Double::doubleValue).sum();

        //52.612
        System.out.println(deductInComing);
    }

    private static void joining(Stream<Production> stream)
    {
        String result = stream.collect(
                Collectors.mapping(p -> p.getName(),
                        Collectors.joining())
        );
        assert result.equals("T-Shirtclothshoehatclothshoe");
    }

    private static void joiningWithDelimiter(Stream<Production> stream)
    {
        String result = stream.collect(
                Collectors.mapping(p -> p.getName(),
                        Collectors.joining("#"))
        );
        assert result.equals("T-Shirt#cloth#shoe#hat#cloth#shoe");
    }

    private static void joiningWithDelimiterAndPrefixAndSuffix(Stream<Production> stream)
    {
        String result = stream.collect(
                Collectors.mapping(p -> p.getName(),
                        Collectors.joining(",", "(", ")"))
        );

        assert result.equals("(T-Shirt,cloth,shoe,hat,cloth,shoe)");
    }

    private static void collectingAndThen(Stream<Production> stream)
    {
        Double averagePriceByVND = stream.collect(
                Collectors.collectingAndThen(
                        Collectors.averagingDouble(Production::getPrice),
                        p -> p * 3264.4791d
                ));
        System.out.println(averagePriceByVND);
    }


    private static void averagingDouble(Stream<Production> stream)
    {
        Double averagePrice = stream.collect(Collectors.averagingDouble(Production::getPrice));
        System.out.println(averagePrice);
    }

    private static void averagingInt(Stream<Production> stream)
    {
        Double averagePrice = stream.collect(Collectors.averagingInt(p -> (int) p.getPrice()));
        System.out.println(averagePrice);
    }

    private static void averagingLong(Stream<Production> stream)
    {
        Double averagePrice = stream.collect(Collectors.averagingLong(p -> (long) p.getPrice()));
        System.out.println(averagePrice);
    }
}