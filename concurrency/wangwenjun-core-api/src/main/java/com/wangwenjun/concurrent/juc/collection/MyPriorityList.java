package com.wangwenjun.concurrent.juc.collection;

import java.util.Comparator;
import java.util.Objects;

public class MyPriorityList<E extends Comparable<E>>
{
    private static class Node<T extends Comparable<T>>
    {
        private T value;
        private Node<T> next;

        private Node(T value, Node<T> next)
        {
            this.value = value;
            this.next = next;
        }

        private Node(T value)
        {
            this(value, null);
        }

        public T getValue()
        {
            return value;
        }

        public Node<T> getNext()
        {
            return next;
        }

        public void setValue(T value)
        {
            this.value = value;
        }

        public void setNext(Node<T> next)
        {
            this.next = next;
        }
    }

    private Node<E> header;

    private int size;

    private final Comparator<E> comparator;

    public MyPriorityList(Comparator<E> comparator)
    {
        this.comparator = Objects.requireNonNull(comparator);
        this.header = null;
    }

    public boolean isEmpty()
    {
        return header == null;
    }

    public void clear()
    {
        this.size = 0;
        this.header = null;
    }

    public int size()
    {
        return this.size;
    }

    public void add(E e)
    {
        final Node<E> newNode = new Node<>(e);
        Node<E> current = this.header;
        Node<E> previous = null;
        while (current != null && e.compareTo(current.getValue()) > 0)
        {
            previous = current;
            current = current.getNext();
        }
        if (previous == null)
        {
            this.header = newNode;
        } else
        {
            previous.setNext(newNode);
        }
        newNode.setNext(current);
        this.size++;
    }

    public E peekFirst()
    {
        if (isEmpty())
        {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support peek operation");
        }

        return header.getValue();
    }

    public E popFirst()
    {
        if (isEmpty())
        {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support pop operation");
        }
        final E value = header.getValue();
        this.header = header.getNext();
        this.size--;
        return value;
    }

    @Override
    public String toString()
    {
        Node<E> node = this.header;
        StringBuilder builder = new StringBuilder("[");
        while (node != null)
        {
            builder.append(node.getValue().toString()).append(",");
            node = node.getNext();
        }
        if (builder.length() > 1)
            builder.deleteCharAt(builder.length() - 1);
        builder.append("]");

        return builder.toString();
    }

    public static void main(String[] args)
    {
        MyPriorityList<Integer> list = new MyPriorityList<Integer>(Comparator.comparingInt(o -> o));
        list.add(45);
        System.out.println(list);
        System.out.println("========================");
        list.add(456);
        list.add(4);
        list.add(48);
        list.add(500);
        System.out.println(list);
        System.out.println("pop first:" + list.popFirst());
        System.out.println(list);
    }
}