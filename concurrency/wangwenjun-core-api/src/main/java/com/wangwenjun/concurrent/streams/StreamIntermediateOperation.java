package com.wangwenjun.concurrent.streams;

import com.google.common.base.Strings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermediateOperation
{

    public static void main(String[] args) throws IOException
    {
        streamOfReduceLength();
    }

    private static void streamOfReduceLength()
    {
        List<String> words = Arrays.asList("Java", "Scala", "Stream",
                "JavaStreamAndReduce", "ScalaStream");

        String maxLengthWords = words.stream()
                .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2)
                .get();
        assert maxLengthWords.equals("JavaStreamAndReduce");
    }

    private static void streamOfReduceSum()
    {
        Integer sum = Stream.of(4, 5, 3, 9).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static void streamOfCollectMap()
    {
        List<String> words = Arrays.asList("Scala", "Java", "Stream",
                "Java", "Alex", "Scala", "Scala");
        Map<String, Long> count = words.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
        System.out.println(count);
    }

    private static void streamOfCollect()
    {
        List<String> list = Stream.of(1, 2, 3, 4, 5, 6)
                .map(i -> Strings.repeat(String.valueOf(i), i))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    private static void streamOfMin()
    {
        Optional<Integer> min = Stream.of(1, 2, 3, 4, 5, 6)
                .min(Comparator.comparingInt(o -> o));
        assert min.get() == 1;
    }

    private static void streamOfMax()
    {
        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5, 6)
                .max(Comparator.comparingInt(o -> o));
        assert max.get() == 6;
    }

    private static void streamOfCount()
    {
        long count = Stream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i % 2 == 0)
                .count();
        assert count == 3;

        long sum = Stream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i % 2 == 0)
                .mapToLong(i -> 1L)
                .sum();
        assert sum == 3;
    }

    private static void streamOfForEach()
    {
        IntStream.range(0, 100)
                .parallel()
                .forEach(System.out::println);
        System.out.println("=====================");
        IntStream.range(0, 100)
                .parallel()
                .forEachOrdered(System.out::println);
    }

    private static void streamOfFind()
    {
        Stream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i > 3)
                .findFirst()
                .ifPresent(r ->
                {
                    assert r == 4;
                });

        assert Stream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i > 3)
                .findAny()
                .isPresent();

        assert !Stream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i > 10)
                .findAny()
                .isPresent();
    }

    private static void streamOfMatch()
    {
        assert Stream.of(1, 2, 3, 4, 5, 6)
                .allMatch(i -> i > 0);

        assert Stream.of(1, 2, 3, 4, 5, 6)
                .anyMatch(i -> i > 5);

        assert Stream.of(1, 2, 3, 4, 5, 6)
                .noneMatch(i -> i > 10);
    }

    private static void streamOfFlatMapFromFile() throws IOException
    {
        Path path = Paths.get("C:\\Users\\wangwenjun\\IdeaProjects\\java-concurrency-book2\\src\\main\\scala\\com\\wangwenjun\\concurrent\\streams\\StreamIntermediateOperation.java");
        Files.lines(path, Charset.forName("UTF-8"))
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .forEach(System.out::println);
    }

    private static void streamOfFlatMap()
    {
        Stream<Integer> newStream = Stream.of(1, 2, 3, 4, 5, 6)
                .flatMap(i -> Stream.of(i, i * 2, i * i));
        newStream.map(i -> i * 10)
                .forEach(System.out::println);
    }

    private static void streamOfSorted()
    {
//        Stream.of(2, 4535, 345, 565667, 2424, 565)
//                .sorted()
//                .forEach(System.out::println);

        Stream.of(new Entity("Java"), new Entity("Scala"), new Entity("Java"))
                .sorted(Comparator.comparing(o -> o.value))
                .forEach(System.out::println);
    }

    private static void streamOfPeek()
    {
        int result = IntStream.range(0, 10)
                .peek(System.out::println)
                .map(i -> i * 2)
                .peek(System.out::println)
                .filter(i -> i > 10)
                .peek(System.out::println)
                .sum();
        System.out.println(result);
    }

    private static void streamOfSkip()
    {
        IntStream.range(0, 10)
                .skip(10)
                .forEach(System.out::println);
    }

    private static void streamOfMap()
    {
        Stream.of(2, 4535, 345, 565667, 2424, 565)
                .map(i -> new int[]{i, String.valueOf(i).length()})
                .forEach(entry -> System.out.printf("%d is %d digits.\n", entry[0], entry[1]));
    }

    private static void streamOfLimit()
    {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .limit(30)
                .forEach(System.out::println);
    }

    private static void streamOfDistinct()
    {
        Stream<Entity> stream = Stream.of(new Entity("Java"), new Entity("Scala"), new Entity("Java"));
        stream.distinct().forEach(System.out::println);
    }

    private static void streamOfFiltering()
    {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }

    static class Entity
    {
        private final String value;

        Entity(String value)
        {
            this.value = value;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entity entity = (Entity) o;

            return value != null ? value.equals(entity.value) : entity.value == null;
        }

        @Override
        public int hashCode()
        {
            return value != null ? value.hashCode() : 0;
        }
    }
}
