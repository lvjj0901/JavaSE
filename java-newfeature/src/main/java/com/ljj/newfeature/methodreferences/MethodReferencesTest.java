package com.ljj.newfeature.methodreferences;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-10 7:10 p.m.
 * @Version 1.0
 */
public class MethodReferencesTest {
    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1(){
        //lambda
        Consumer<String> con1 = s -> System.out.println(s);
        con1.accept("Hello Lambda!!");

        //method references 1
        PrintStream out = System.out;
        Consumer<String> con2 = out::println;
        con2.accept("Method references-->instance::method");

        //method references 2
        Consumer<String> con3 = System.out::println;
        con3.accept("Hello Method references-->instance::method");
    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2(){
        Employee employee = new Employee(1,"Andy",'M',9,300);
        //Lambda
        Supplier<String> supplier1 = () -> employee.getName();
        System.out.println(supplier1.get());

        //Method references
        Supplier<String> supplier2 = employee::getName;

        System.out.println(supplier2.get());
    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test3(){
        //Lambda
        Comparator<Integer> com1 = (t1,t2)->t1.compareTo(t2);
        System.out.println(com1.compare(10, 9));

        //Method references

        Comparator<Integer> com2 = Integer::compareTo;
        System.out.println(com2.compare(10, 9));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4(){
        Function<Double,Long> fun1 = d->Math.round(d);
        System.out.println(fun1.apply(1.4));

        Function<Double,Long> fun2 = Math::round;

        System.out.println(fun2.apply(1.4));
    }
    // 情况三：类 :: 实例方法  (有难度)
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test5(){
        Comparator<String> com1 = (s1,s2)->s1.compareTo(s2);
        System.out.println(com1.compare("abc", "abd"));

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abc", "abd"));
    }
    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test6(){
        BiPredicate<String,String> bp1 = (s1,s2)->s1.equals(s2);
        System.out.println(bp1.test("ab", "ab"));

        BiPredicate<String,String> bp2 = String::equals;
        System.out.println(bp2.test("ab", "ab"));
    }
    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test7(){
        Employee employee = new Employee(1,"Andy",'M',9,300);

        Function<Employee,String> fun1 = emp -> emp.getName();
        System.out.println(fun1.apply(employee));

        Function<Employee,String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee));
    }
}

class Employee{
    private int id;
    private String name;
    private char gender;
    private int age;
    private double salary;

    public Employee(int id, String name, char gender, int age, double salary) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
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
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", salary=" + salary
                + "]";
    }
}