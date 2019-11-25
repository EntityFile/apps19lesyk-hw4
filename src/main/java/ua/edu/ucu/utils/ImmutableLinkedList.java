package ua.edu.ucu.utils;

public final class ImmutableLinkedList implements ImmutableList {
    private Node arrayList;
    private int size;

    public ImmutableLinkedList() {
        arrayList = new Node();
        size = 0;
    }

    public ImmutableLinkedList(Object[] arraySeries) {
        int i = 0;
        int ind = arraySeries.length - 1;
        arrayList = new Node();
        Object[] lst = new Object[arraySeries.length];

        for (int k = 0; k < arraySeries.length; k++) {
            lst[k] = arraySeries[ind];
            ind--;
        }

        for (Object item : lst) {
            arrayList.setData(item);
            if (i < lst.length - 1) {
                arrayList = new Node(null, arrayList);
            }
            i++;
        }

        size = i;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        this.checkIndexStrict(index);

        if (size == 0) {
            return new ImmutableLinkedList(c);
        } else {
            Object[] newArrayList = new Object[c.length + size];
            Object[] arr = this.toArray();

            System.arraycopy(arr, 0, newArrayList, 0, index);
            System.arraycopy(c, 0, newArrayList, index, c.length);
            System.arraycopy(arr, index, newArrayList, index + c.length,
                    size - index);
            return new ImmutableLinkedList(newArrayList);
        }
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(size, c);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        this.checkIndexStrict(index);

        Object[] newArrayList = new Object[size + 1];
        Object[] arr = this.toArray();

        System.arraycopy(arr, 0, newArrayList, 0, index);
        newArrayList[index] = e;
        System.arraycopy(arr, index, newArrayList, index + 1, size - index);

        return new ImmutableLinkedList(newArrayList);
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.add(size, e);
    }

    @Override
    public Object get(int index) {
        this.checkIndexUnStrict(index);

        Node nd = arrayList;
        Object element = nd.getData();
        for (int i = 0; i <= index; i++) {
            element = nd.getData();
            nd = nd.getNext();
        }
        return element;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        this.checkIndexUnStrict(index);

        Object[] newArrayList = new Object[size - 1];
        Object[] arr = this.toArray();

        System.arraycopy(arr, 0, newArrayList, 0, index);
        System.arraycopy(arr, index + 1, newArrayList, index, size - index - 1);

        return new ImmutableLinkedList(newArrayList);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        this.checkIndexUnStrict(index);

        Object[] arr = this.toArray();
        arr[index] = e;

        return new ImmutableLinkedList(arr);
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node nd = arrayList;

        while (i < size) {
            if (nd.getData().equals(e)) {
                return i;
            }
            nd = nd.getNext();
            i++;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        int i = 0;
        Node nd = arrayList;

        if (size == 0) {
            return "";
        }

        while (i < size) {
            str.append(nd.getData());
            if (i < size - 1) {
                str.append(",");
            }
            nd = nd.getNext();
            i++;
        }

        return new String(str);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node nd = arrayList;

        for (int i = 0; i < size; i++) {
            array[i] = nd.getData();
            nd = nd.getNext();
        }

        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(size, e);
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(size - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(size - 1);
    }

    private void checkIndexUnStrict(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index!");
        }
    }

    private void checkIndexStrict(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index!");
        }
    }
}
