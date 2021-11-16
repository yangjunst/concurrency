package com.wangwenjun.concurrent.streams;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomCollector<T> implements Collector<T, List<T>, List<T>>
{
    @Override
    public Supplier<List<T>> supplier()
    {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator()
    {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner()
    {
        return (lList, rList) ->
        {
            lList.addAll(rList);
            return lList;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher()
    {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics()
    {
        return EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH);
    }

    private static <T> Collector<T, List<T>, List<T>> custom()
    {
        return Collector.of(ArrayList::new, List::add, (lList, rList) ->
                {
                    lList.addAll(rList);
                    return lList;
                }, Function.identity(), Characteristics.UNORDERED,
                Characteristics.CONCURRENT,
                Characteristics.IDENTITY_FINISH);
    }

    public static void main(String[] args)
    {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result = stream.collect(custom());
        System.out.println(result);
    }
}