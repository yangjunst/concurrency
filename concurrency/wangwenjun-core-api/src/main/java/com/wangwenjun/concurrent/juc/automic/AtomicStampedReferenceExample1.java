package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicStampedReferenceExample1
{

    private static class LinkedStack<E>
    {
        static class Node<T>
        {
            T value;
            Node<T> next;

            Node(T value, Node<T> next)
            {
                this.value = value;
                this.next = next;
            }
        }

        private final AtomicReference<Node<E>> top = new AtomicReference<>();

        void push(E e)
        {
            this.top.updateAndGet(eNode -> new Node<>(e, eNode));
        }

        E pop()
        {
            if (top.get() == null)
                return null;
            return top.getAndSet(top.get().next).value;
        }

        E peek()
        {
            if (top.get() == null)
                return null;
            return top.get().value;
        }

        boolean isEmpty()
        {
            return top.get() == null;
        }
    }

    static class Element
    {
        private String value;

        Element(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return value;
        }
    }

}
