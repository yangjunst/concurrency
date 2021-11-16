package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.atomic.AtomicStampedReference;

public class LockFreeLinkedList<E>
{
    private static class Node<E>
    {
        E element;
        volatile Node<E> next;

        Node(E element)
        {
            this.element = element;
        }

        @Override
        public String toString()
        {
            return element == null ? "" : element.toString();
        }
    }

    private AtomicStampedReference<Node<E>> headRef = null;

    public LockFreeLinkedList()
    {
        this.headRef = new AtomicStampedReference<>(null, 0);
    }

    public boolean isEmpty()
    {
        return this.headRef.getReference() == null;
    }

    public void clear()
    {
        this.headRef.set(null, headRef.getStamp() + 1);
    }

    public void add(E element)
    {
        if (null == element) throw new NullPointerException("The element is null");
        Node<E> previousNode;
        int previousStamp;
        Node<E> newNode = new Node<>(element);
        do
        {
            previousNode = this.headRef.getReference();
            previousStamp = this.headRef.getStamp();
            newNode.next = previousNode;
        } while (!this.headRef.compareAndSet(previousNode, newNode, previousStamp, previousStamp + 1));
    }

    public E peekFirst()
    {
        return isEmpty() ? null : this.headRef.getReference().element;
    }

    public E removeFirst()
    {
        if (isEmpty()) return null;
        Node<E> currentNode;
        int currentStamp;
        Node<E> nextNode;
        do
        {
            currentNode = this.headRef.getReference();
            currentStamp = this.headRef.getStamp();
            if (currentNode == null)
                break;
            nextNode = currentNode.next;
        } while (!this.headRef.compareAndSet(currentNode, nextNode, currentStamp, currentStamp + 1));

        return currentNode == null ? null : currentNode.element;
    }

    public long count()
    {
        long count = 0;
        Node<E> currentNode = this.headRef.getReference();
        while (currentNode != null)
        {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }


}
