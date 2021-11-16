package com.wangwenjun.concurrent.streams;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CreateStreams
{
    private static Stream<Integer> fromValues()
    {
        return Stream.of(1, 2, 3, 4);
    }

    private static Stream<Integer> fromBuilder()
    {
        return Stream.<Integer>builder()
                .add(1)
                .add(2)
                .add(3)
                .add(4)
                .build();
    }

    private static Stream<File> emptyStream()
    {
        return Stream.empty();
    }

    private static Stream<Integer> infiniteStreamByGenerate()
    {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(10));
    }

    private static Stream<Integer> infiniteStreamByIterate()
    {
        return Stream.iterate(100, seed -> seed + 1);
    }

    private static IntStream infiniteNumericStreamByIterate()
    {
        return IntStream.iterate(100, seed -> seed + 1);
    }

    private static IntStream rangeNumericStream()
    {
        return IntStream.range(1, 10);
    }

    private static IntStream rangeClosedNumericStream(){
        return IntStream.rangeClosed(1,10);
    }

    private static class Entity
    {
    }

    private static Stream<Entity> fromArrays()
    {
        return Arrays.stream(new Entity[]{new Entity(), new Entity()});
    }

    private static Stream<String> fromCollection()
    {
        Collection<String> list = Arrays.asList("Hello", "Stream");
        return list.stream();
    }

    private static Stream<String> fromFile() throws IOException
    {
        return Files.lines(Paths.get("test.txt"), Charset.forName("UTF-8"));
    }

    private static Stream<Map.Entry<String, String>> fromMap()
    {
        return new HashMap<String, String>()
        {
            {
                put("Hello", "Stream");
                put("Java", "Programming");
            }
        }.entrySet().stream();
    }

    private static IntStream fromString()
    {
        String line = "Hello i am Stream";
        return line.chars();
    }

    public static void main(String[] args)
    {
        rangeClosedNumericStream().forEach(System.out::println);
    }
}
