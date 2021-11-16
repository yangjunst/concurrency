package com.wangwenjun.concurrent.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HelloStream
{
    public static void main(String[] args)
    {
        List<Book> books = Arrays.asList(new Book("Java Programming",10.0d,"Programming"));
//        final List<String> result = books.stream()
//                .filter(book->book.category.equals("Programming"))
//                .sorted(Comparator.comparing(Book::getPrice))
//                .map(Book::getName)
//                .collect(Collectors.toList());

        final List<String> result = books.parallelStream()
                .filter(book->book.category.equals("Programming"))
                .sorted(Comparator.comparing(Book::getPrice))
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    static class Book{
        private String name;
        private double price;
        private String category;

        public Book(String name, double price, String category)
        {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public double getPrice()
        {
            return price;
        }

        public void setPrice(double price)
        {
            this.price = price;
        }

        public String getCategory()
        {
            return category;
        }

        public void setCategory(String category)
        {
            this.category = category;
        }
    }
}
