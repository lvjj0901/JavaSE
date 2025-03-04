package com.ljj.datastructure;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-02-27 9:23 p.m.
 * @Version 2.0
 */
public class JasonArray {
    private Object[] elementData;
    private int size;
    public JasonArray(int capacity){
        this.elementData = new Object[capacity];
    }

    /**
     * add an element in the array
     * @param value
     */
    public void add(Object value){
        if(size >= elementData.length){
            throw new RuntimeException("Array is full, no more space!!!");
        }
        elementData[size++] = value;
    }
    /**
     * 查询元素value在数组中的索引位置
     * get the index of value in the array
     * @param value
     * @return -1 denotes there is no such element inside the array
     */
    public int find(Object value){
        for (int i = 0; i < size; i++) {
            if(elementData[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    /**
     * remove the first occurrence of the value from the array
     * @param value
     * @return false denotes there is no such element inside the array
     */
    public boolean delete(Object value){
        int index = find(value);
        if (-1 == index){
            return false;
        }
        for (int i = index; i < size -1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return true;
    }
    /**
     * 将数组中首次出现的oldValue替换为newValue
     * replace the oldValue of the first occurrence with newValue in the array
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean update(Object oldValue,Object newValue){
        int i = find(oldValue);
        if(i==-1){
            return false;
        }
        elementData[i] = newValue;
        return true;
    }
    /**
     * iterator over the array
     */
    public void print(){
        System.out.print("[");
        for (int i = 0; i < size-1; i++) {
            System.out.print(elementData[i] + ",");
        }
        System.out.print(elementData[size-1]);
        System.out.print("]");
        System.out.println();
    }
//    private Object[] data;
//    private int pointer = 0;
//    private int capacity;
//
//    public JasonArray(int capacity) {
//        this.capacity = capacity;
//        this.data = new Object[capacity];
//    }
//
//    public void add(Object obj) {
//        if (pointer < capacity) {
//            data[pointer] = obj;
//            pointer++;
//        } else {
//            throw new RuntimeException("ArrayIndexOutOfBoundsException");
//        }
//    }
//
//    public void remove(int index) {
//        if ((index > -1) && (index < capacity)) {
//            //delete element
//            data[index] = null;
//            //move elements
//            for (int i = index; i <= pointer; i++) {
//                data[index] = data[index + 1];
//            }
//            //the last element is null
//            data[pointer] = null;
//            pointer--;
//        } else {
//            throw new RuntimeException("ArrayIndexOutOfBoundsException");
//        }
//    }
//
//    public void update(int index, Object obj) {
//        if ((index > -1) && (index < capacity)) {
//            data[index] = obj;
//        } else {
//            throw new RuntimeException("ArrayIndexOutOfBoundsException");
//        }
//    }
//
//    public Object get(int index) {
//        if ((index > -1) && (index < capacity)) {
//            return data[index];
//        } else {
//            throw new RuntimeException("ArrayIndexOutOfBoundsException");
//        }
//    }
//
//    public int size() {
//        return pointer;
//    }
//
//    public void printArray() {
//        System.out.print("[");
//        for (int i = 0; i < pointer; i++) {
//            if (i != (pointer-1)) {
//                System.out.print(data[i] + ",");
//            } else {
//                System.out.print(data[i]);
//            }
//        }
//        System.out.print("]");
//        System.out.println();
//    }
}
