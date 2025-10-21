package support;

public class SinglyLinkedList<E> {
    private LLNode<E> head;
    private LLNode<E> tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        LLNode<E> newNode = new LLNode<>(e);
        newNode.setLink(head);
        head = newNode;
        if (size == 0) tail = head;
        size++;
    }

    public void addLast(E e) {
        LLNode<E> newNode = new LLNode<>(e);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setLink(newNode);
            tail = newNode;
        }
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(e);
            return;
        }
        if (index == size) {
            addLast(e);
            return;
        }
        LLNode<E> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.getLink();
        }
        LLNode<E> newNode = new LLNode<>(e);
        newNode.setLink(prev.getLink());
        prev.setLink(newNode);
        size++;
    }

    public E removeFirst() {
        if (size == 0) throw new IllegalStateException("List is empty");
        E value = head.getInfo();
        head = head.getLink();
        size--;
        if (size == 0) tail = null;
        return value;
    }

    public E removeLast() {
        if (size == 0) throw new IllegalStateException("List is empty");
        if (size == 1) {
            return removeFirst();
        }
        LLNode<E> prev = head;
        while (prev.getLink() != tail) {
            prev = prev.getLink();
        }
        E value = tail.getInfo();
        prev.setLink(null);
        tail = prev;
        size--;
        return value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();
        LLNode<E> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.getLink();
        }
        LLNode<E> toRemove = prev.getLink();
        prev.setLink(toRemove.getLink());
        size--;
        return toRemove.getInfo();
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getLink();
        }
        return current.getInfo();
    }

    public E set(int index, E e) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getLink();
        }
        E old = current.getInfo();
        current.setInfo(e);
        return old;
    }

    public boolean contains(Object o) {
        LLNode<E> current = head;
        while (current != null) {
            if ((o == null && current.getInfo() == null) ||
                (o != null && o.equals(current.getInfo()))) {
                return true;
            }
            current = current.getLink();
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
