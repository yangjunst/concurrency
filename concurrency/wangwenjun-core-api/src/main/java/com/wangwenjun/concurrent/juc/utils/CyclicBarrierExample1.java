package com.wangwenjun.concurrent.juc.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;

public class CyclicBarrierExample1
{
    public static void main(String[] args)
            throws InterruptedException, BrokenBarrierException
    {
        final int[] products = getProductsByCategoryId();
        List<ProductPrice> list = Arrays.stream(products).mapToObj(ProductPrice::new).collect(toList());
        final CyclicBarrier barrier = new CyclicBarrier(list.size()+1);
        list.forEach(pp ->
             new Thread(() ->
             {
                 System.out.println(pp.getProdID() + "-> start calculate price.");
                 try
                 {
                     TimeUnit.SECONDS.sleep(current().nextInt(10));
                     if (pp.prodID % 2 == 0)
                     {
                         pp.setPrice(pp.prodID * 0.9D);
                     } else
                     {
                         pp.setPrice(pp.prodID * 0.71D);
                     }
                     System.out.println(pp.getProdID() + "->price calculate completed.");
                 } catch (InterruptedException e)
                 {
                 } finally
                 {
                     try
                     {
                         barrier.await();
                     } catch (InterruptedException | BrokenBarrierException e)
                     {
                     }
                 }
             }).start()
        );

        barrier.await();
        System.out.println("all of prices calculate finished.");
        list.forEach(System.out::println);
    }

    private static int[] getProductsByCategoryId()
    {
        return IntStream.rangeClosed(1, 10).toArray();
    }

    private static class ProductPrice
    {
        private final int prodID;
        private double price;

        private ProductPrice(int prodID)
        {
            this(prodID, -1);
        }

        private ProductPrice(int prodID, double price)
        {
            this.prodID = prodID;
            this.price = price;
        }

        int getProdID()
        {
            return prodID;
        }

        void setPrice(double price)
        {
            this.price = price;
        }

        @Override
        public String toString()
        {
            return "ProductPrice{" +
                    "prodID=" + prodID +
                    ", price=" + price +
                    '}';
        }
    }
}