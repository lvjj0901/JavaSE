package com.ljj.datastructure;

import org.w3c.dom.Node;

import java.util.LinkedList;

/**
 * Description:
 * Custom a singly linked list
 *
 * @Author Jason Lyu
 * @Create 2025-03-03 10:44 p.m.
 * @Version 1.0
 */
public class JasonSinglyLinkedList {
    public static void main(String[] args) {
        JasonSinglyLinkedList list = new JasonSinglyLinkedList();
        list.add("0AA");
        list.add("1BB");
        list.add(null);
        list.add("2CC");
        list.add("3DD");
        list.add("4EE");
        list.add("5FF");
//        System.out.println(list.find("3DD"));
        list.remove(null);
        System.out.println();
//        LinkedList linkedList = new LinkedList();
//        linkedList.add(null);
//        System.out.println(linkedList.toString());

    }

    private Node head;
    private int size = 0;

    /**
     * insert new element using the tail insertion method
     *
     * @param element
     */
    public void add(Object element) {
        Node newNode = new Node(element, null);
        if (head == null) {
            head = newNode;
        } else {
            findTail(head).next = newNode;
        }
        size++;
    }

    /**
     * find tail element using recursion
     *
     * @param node
     * @return
     */
    private Node findTail(Node node) {
        if (node == null) {
            return null;
        } else if (node.next == null) {
            return node;
        } else {
            return findTail(node.next);
        }
    }

    public void remove(Object obj) {
        if(head.data==null||obj==null){
            if (head.data==obj) {
                head = head.next;
                size--;
            } else {
                for (Node node = head; node != null; node = node.next) {
                    if (node.next.data==obj) {
                        node.next = node.next.next;
                        size--;
                        break;
                    }
                }
            }
        }else {
            if (head.data.equals(obj)) {
                head = head.next;
                size--;
            } else {
                for (Node node = head; node != null; node = node.next) {
                    if (node.next.data.equals(obj)) {
                        node.next = node.next.next;
                        size--;
                        break;
                    }
                }
            }
        }
    }
    public void modify(Object oldVal,Object newVal){
        for(Node node=head;node!=null;node=node.next){
            if(node.data.equals(oldVal)){
                node.data = newVal;
                break;
            }
        }
    }
    public int find(Object obj){
        int index = 0;
        for(Node node=head;node!=null;node=node.next){
            if(node.data.equals(obj)){
                return index;
            }
            index++;
        }
        return -1;
    }

    private static class Node {
        private Object data;
        private Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
