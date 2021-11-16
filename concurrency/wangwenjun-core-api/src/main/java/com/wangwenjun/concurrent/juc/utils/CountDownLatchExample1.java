package com.wangwenjun.concurrent.juc.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;

public class CountDownLatchExample1
{
    public static void main(String[] args) throws InterruptedException
    {
        final int[] products = getProductsByCategoryId();
        List<ProductPrice> list = Arrays.stream(products).mapToObj(ProductPrice::new).collect(toList());
        final CountDownLatch latch = new CountDownLatch(products.length);
        list.forEach(pp ->
                new Thread(() ->
                {
                    System.out.println(pp.getProdID() + "  start calculate price.");
                    try
                    {
                        int time=current().nextInt(10);
                        TimeUnit.SECONDS.sleep(time);
                        if (pp.prodID % 2 == 0)
                        {
                            pp.setPrice(pp.prodID * 0.9D);
                        } else
                        {
                            pp.setPrice(pp.prodID * 0.71D);
                        }
                        System.out.println(pp.getProdID() + " ->price calculate completed."+"-->"+time+"s");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    } finally
                    {
                        latch.countDown();
                    }
                }).start()
        );
        long start=System.currentTimeMillis();
        latch.await();
        System.out.println("all of prices calculate finished."+"--->"+((System.currentTimeMillis()-start)/1000)+"s");
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
