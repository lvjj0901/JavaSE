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
}
