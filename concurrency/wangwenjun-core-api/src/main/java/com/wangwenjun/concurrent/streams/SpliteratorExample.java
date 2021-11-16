package com.wangwenjun.concurrent.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SpliteratorExample
{
    public static void main(String[] args)
    {
        customSpliterator();
    }

    private static void customSpliterator()
    {
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15,
                16, 17, 18, 19, 20,
                21, 22, 23, 24, 25,
                26, 27, 28, 29, 30
        };

        MySpliterator<Integer> mySpliterator = new MySpliterator<>(ints);

       /* Spliterator s1 = mySpliterator.trySplit();
        Spliterator s2 = mySpliterator.trySplit();

        s1.forEachRemaining(System.out::println);
        System.out.println("==================");

        s2.forEachRemaining(System.out::println);
        System.out.println("==================");

        mySpliterator.forEachRemaining(System.out::println);*/

/*        Stream<Integer> stream = StreamSupport.stream(mySpliterator, true);
        int sum = stream.reduce(0, Integer::sum);

        assert sum == Stream.of(ints).reduce(0, Integer::sum);*/

        Stream<Long> stream = Stream.iterate(0L, l -> l + 1L)
                .limit(1_000_000);

        Spliterator<Long> spliterator = stream.spliterator();

        Spliterator<Long> s1 = spliterator.trySplit();

        assert s1 == null;

    }

    private static void listSpliterator()
    {
        List<String> list = new ArrayList<>();
        Spliterator<String> spliterator = list.spliterator();
        int expected = Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        assert expected == spliterator.characteristics();
    }

    static class MySpliterator<T> implements Spliterator<T>
    {

        private final T[] elements;
        private int currentIndex = 0;
        private final int CAPACITY;

        public MySpliterator(T[] elements)
        {
            this.elements = elements;
            this.CAPACITY = elements.length;
        }


        @Override
        public boolean tryAdvance(Consumer<? super T> action)
        {
            action.accept(elements[currentIndex++]);
            return currentIndex < CAPACITY;
        }

        @Override
        public Spliterator<T> trySplit()
        {
            int remainingSize = CAPACITY - currentIndex;
            if (remainingSize < 10)
            {
                return null;
            }
            int middleSize = (remainingSize) / 2;
            T[] newElements = (T[]) new Object[middleSize];
            System.arraycopy(elements, currentIndex, newElements, 0, middleSize);
            final MySpliterator<T> spliterator = new MySpliterator<>(newElements);

            this.currentIndex = currentIndex + middleSize;
            return spliterator;
        }

        @Override
        public long estimateSize()
        {
            return CAPACITY - currentIndex;
        }

        @Override
        public int characteristics()
        {
            return Spliterator.ORDERED | Spliterator.SIZED
                    | Spliterator.SUBSIZED | Spliterator.NONNULL
                    | Spliterator.IMMUTABLE;
        }
    }
}
