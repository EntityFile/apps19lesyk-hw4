package ua.edu.ucu.utils;

public class Node {
    private Object data;
    private Node next;

    public Node(Object dt, Node nxt) {
        this.data = dt;
        this.next = nxt;
    }

    public Node() {
        this.data = null;
        this.next = null;
    }

    public void setData(Object dt) {
        this.data = dt;
    }

    public void setNext(Node nxt) {
        this.next = nxt;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

}
