package com.ljj.newfeature.lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-08 10:50 a.m.
 * @Version 1.0
 */
public class LambdaFunctionTest {
    public static void main(String[] args) {
        //convert the initial letter of the specified string to  upper case
        Function<String,String> f = s->s.substring(0,1).toUpperCase()+s.substring(1);
        String s = "jasonLyu";
        System.out.println("-----before action");
        System.out.println(s);
        System.out.println("-----after action");
        System.out.println(f.apply(s));
    }
    @Test
    public void testMapFunction(){
        HashMap<Integer, Employee> map = new HashMap<>();
        map.put(1,new Employee(1,"Andy",5000.00));
        map.put(2,new Employee(2,"Wanda",6000.00));
        map.put(3,new Employee(3,"Jason",7000.00));
        System.out.println("Before get a raise!");
        map.forEach((k,v)-> System.out.println(k+"->"+v));

        map.replaceAll((k,v)->{
            v.setSalary(v.getSalary()+5);
            return v;
        });
        System.out.println("After get a raise!");
        map.forEach((k,v)->System.out.println(k+"->"+v));
    }
}

class Employee{
    private int id;
    private String name;
    private double salary;

    public Employee(){}
    public Employee(int id,String name,double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
