package com.maomao.learn.concurrcy.collection;

public class MyList<E>
{
    private static class Node<E>
    {
        private final E value;
        private final Node<E> next;

        private Node(E value, Node<E> next)
        {
            this.value = value;
            this.next = next;
        }

        public E getValue()
        {
            return value;
        }

        public Node<E> getNext()
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
        Node node = new Node(e, header);
        this.header = node;
        this.size++;
    }

    public E peekFirst()
    {
        if (isEmpty())
        {
            throw new IndexOutOfBoundsException("Ehe linked list is empty now, can't support peek operation");
        }

        return header.getValue();
    }

    public E popFirst()
    {
        if (isEmpty())
        {
            throw new IndexOutOfBoundsException("Ehe linked list is empty now, can't support pop operation");
        }
        final E value = header.getValue();
        this.header = header.getNext();
        this.size--;
        return value;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = this.header;
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