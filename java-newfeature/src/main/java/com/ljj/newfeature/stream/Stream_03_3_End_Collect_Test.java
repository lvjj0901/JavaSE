package com.ljj.newfeature.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-13 4:46 p.m.
 * @Version 1.0
 */
public class Stream_03_3_End_Collect_Test {
    @Test
    public void test1(){
        List<Integer> list = Stream.of(1, 2, 4, 5, 7, 8)
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(list);
    }
    @Test
    public void test2(){
        Set<Integer> set = Stream.of(1, 2, 2, 3, 3, 4)
                .collect(Collectors.toSet());
        System.out.println(set);
    }
    @Test
    public void test3(){
        Collection<Integer> collection = Stream.of(1, 1, 2, 3, 4, 5)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collection);

        HashSet<Integer> hashSet = Stream.of(1, 1, 2, 3, 4, 5)
                .collect(Collectors.toCollection(HashSet::new));

        System.out.println(hashSet);
    }
    @Test
    public void test4(){
        Long aLong = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.counting());
        System.out.println(aLong);
    }
    @Test
    public void test5(){
        Double aDouble = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(aDouble);

    }

    @Test
    public void test6(){
        Double aDouble = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);

    }
    @Test
    public void test7(){
        DoubleSummaryStatistics doubleSummaryStatistics = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(doubleSummaryStatistics.getAverage());

    }
    @Test
    public void test8(){
        String s = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .map(Employee::getName).collect(Collectors.joining());
        System.out.println(s);
    }

    @Test
    public void test9(){
        Optional<Employee> collect = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(collect.get());
    }
    @Test
    public void test10(){
        Optional<Employee> collect = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(collect.get());
    }
    @Test
    public void test11(){
        Optional<Integer> optional = Stream.of(8001, 18000, 8000, 9000, 15000, 11000)
                .collect(Collectors.reducing((x1, x2) -> x1 + x2));
        System.out.println(optional.get());
    }
    @Test
    public void test12(){
        Double aDouble = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.reducing(0.0, Employee::getSalary, (x1, x2) -> x1 + x2));
        System.out.println(aDouble);
    }

    @Test
    public void test13(){
        Object[] collect = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .map(Employee::getId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::toArray));
        System.out.println(Arrays.toString(collect));
    }
    @Test
    public void test14(){
        Map<Character, List<Employee>> collect = Stream.of(new Employee(1, "张三", '男', 33, 8000,false),
                        new Employee(2, "翠花", '女', 23, 18000,true),
                        new Employee(3, "无能", '男', 46, 8000,true),
                        new Employee(4, "李四", '女', 23, 9000,false),
                        new Employee(5, "老王", '男', 23, 15000,false),
                        new Employee(6, "大嘴", '男', 23, 11000,true))
                .collect(Collectors.groupingBy(Employee::getGender));
        System.out.println(collect.toString());
    }
    @Test
    public void test15(){
        Map<Boolean, List<Employee>> collect = Stream.of(new Employee(1, "张三", '男', 33, 8000, false),
                        new Employee(2, "翠花", '女', 23, 18000, true),
                        new Employee(3, "无能", '男', 46, 8000, true),
                        new Employee(4, "李四", '女', 23, 9000, false),
                        new Employee(5, "老王", '男', 23, 15000, false),
                        new Employee(6, "大嘴", '男', 23, 11000, true))
                .collect(Collectors.partitioningBy(Employee::getMarried));
        System.out.println(collect.toString());
    }
}
class Employee implements Comparable{
    private int id;
    private String name;
    private char gender;
    private int age;
    private double salary;

    private Boolean married;

    public Employee(int id, String name, char gender, int age, double salary,Boolean married) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.married = married;
    }
    public Employee() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", salary=" + salary
                + "]";
    }

    @Override
    public int compareTo(Object o) {
        if(this==o){
            return 0;
        }

        if(o instanceof Employee){
            Employee e = (Employee) o;
            return Double.compare(this.getSalary(),e.getSalary());
        }
        throw new RuntimeException("Class cast error!");
    }
}