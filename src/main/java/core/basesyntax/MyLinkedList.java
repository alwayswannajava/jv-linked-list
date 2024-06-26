package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {

    }

    @Override
    public void add(T value) {
        Node<T> node = new Node<>(null, value, null);
        if (head == null) {
            head = node;
            tail = head;
            size++;
        } else {
            node = new Node<>(tail, value, null);
            node.prev.next = node;
            tail = node;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        checkIndexRangeInAdd(index);
        if (head == null) {
            add(value);
        } else {
            Node<T> newNode = new Node<>(tail, value, null);
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            tail = newNode;
            size++;
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return searchNodeByIndex(index).element;
    }

    @Override
    public T set(T value, int index) {
        checkIndexRange(index);
        Node<T> foundNodeByIndex = searchNodeByIndex(index);
        T oldElement = foundNodeByIndex.element;
        foundNodeByIndex.element = value;
        return oldElement;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T removedElement;
        if (index == 0) {
            removedElement = head.element;
            head = head.next;
            size--;
            return removedElement;
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
            size--;
            return currentNode.element;
        }
    }

    @Override
    public boolean remove(T object) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: "
                                                        + index
                                                        + " out of bounds "
                                                        + "for size "
                                                        + size);
        }
    }

    private void checkIndexRangeInAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "
                                                        + index
                                                        + " out of bounds "
                                                        + "for size "
                                                        + size);
        }
    }

    private Node<T> searchNodeByIndex(int index) {
        if (index < size / 2) {
            Node<T> foundNodeByIndexFromHead = head;
            for (int i = 0; i < index; i++) {
                foundNodeByIndexFromHead = foundNodeByIndexFromHead.next;
            }
            return foundNodeByIndexFromHead;
        } else {
            Node<T> foundNodeByIndexFromTail = tail;
            for (int i = size - 1; i > index; i--) {
                foundNodeByIndexFromTail = foundNodeByIndexFromTail.prev;
            }
            return foundNodeByIndexFromTail;
        }
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        private Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
