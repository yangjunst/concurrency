package com.wangwenjun.concurrent.juc.collection;

public class MyList<E>
{
    private static class Node<T>
    {
        private final T value;
        private final Node<T> next;

        private Node(T value, Node<T> next)
        {
            this.value = value;
            this.next = next;
        }

        public T getValue()
        {
            return value;
        }

        public Node<T> getNext()
        {
            return next;
        }
    }

    private Node<E> header;

    private int size;

    public MyList()
    {
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
        Node<E> node = new Node<>(e, header);
        this.header = node;
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
}