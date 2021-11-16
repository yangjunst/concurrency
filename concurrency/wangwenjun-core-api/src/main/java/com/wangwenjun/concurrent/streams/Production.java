package com.wangwenjun.concurrent.streams;

class Production
{
    private final String name;
    private final double price;

    Production(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Production{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}