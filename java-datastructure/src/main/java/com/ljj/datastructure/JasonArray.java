package com.ljj.datastructure;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-02-27 9:23 p.m.
 * @Version 1.0
 */
public class JasonArray {
    private Object[] data;
    private int pointer = 0;
    private int capacity;

    public JasonArray(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    public void add(Object obj) {
        if (pointer < capacity) {
            data[pointer] = obj;
            pointer++;
        } else {
            throw new RuntimeException("ArrayIndexOutOfBoundsException");
        }
    }

    public void remove(int index) {
        if ((index > -1) && (index < capacity)) {
            //delete element
            data[index] = null;
            //move elements
            for (int i = index; i <= pointer; i++) {
                data[index] = data[index + 1];
            }
            //the last element is null
            data[pointer] = null;
            pointer--;
        } else {
            throw new RuntimeException("ArrayIndexOutOfBoundsException");
        }
    }

    public void update(int index, Object obj) {
        if ((index > -1) && (index < capacity)) {
            data[index] = obj;
        } else {
            throw new RuntimeException("ArrayIndexOutOfBoundsException");
        }
    }

    public Object get(int index) {
        if ((index > -1) && (index < capacity)) {
            return data[index];
        } else {
            throw new RuntimeException("ArrayIndexOutOfBoundsException");
        }
    }

    public int size() {
        return pointer;
    }

    public void printArray() {
        System.out.print("[");
        for (int i = 0; i < pointer; i++) {
            if (i != (pointer-1)) {
                System.out.print(data[i] + ",");
            } else {
                System.out.print(data[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
