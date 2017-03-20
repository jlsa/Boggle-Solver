package jsh.boggle.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic stack, using a linked list.
 * Used the Stack.java from Algorithms 4th Edition as example
 * Original Authors Robert Sedgewick and Kevin Wayne
 * Some liberties to myself for to understand how a linkedlist based stack works.
 *
 * Created by Joël Hoekstra on 16/03/2017.
 */
public class Stack<E> implements Iterable<E> {

    private Node<E> head; // the head of the stack
    private int n; // size of the stack

    // helper linked list class
    private static class Node<E> {
        private E element;
        private Node<E> next;
    }

    public Stack() {
        head = null;
        n = 0;
    }

    /**
     * Returns true if this stack is empty
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in this stack
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the element to this stack
     *
     * @param element the element to add
     */
    public void push(E element) {
        Node<E> previous = head;
        head = new Node<E>();
        head.element = element;
        head.next = previous;
        n++;
    }

    /**
     * Removes and returns the element most recently added to this stack
     *
     * @return the element most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        E element = head.element; // save item to return
        head = head.next; // delete head node
        n--;
        return element;
    }

    /**
     * Returns (but does not remove) the element most recently added to this stack
     *
     * @return the element most recently added to
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return head.element;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of elements in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E element : this) {
            s.append(element);
            s.append(" ");
        }

        return s.toString();
    }


    /**
     * Returns an iterator to this stack that iterates through the elements in LIFO order.
     *
     * @return an iterator to this stack that iterates through the elements in LIFO order.
     */
    public Iterator<E> iterator() {
        return new ListIterator<E>(head);
    }

    private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> head) { current = head; }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
    }


}