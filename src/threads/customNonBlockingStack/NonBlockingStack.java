package threads.customNonBlockingStack;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingStack<T> {

    private AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> oldHead;
        Node<T> newHead = new Node<>(value);
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) {
                throw new NoSuchElementException("EmptyStack");
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.value;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

}
