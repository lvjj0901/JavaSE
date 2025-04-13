package com.ljj.newfeature.methodreferences;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-11 11:17 a.m.
 * @Version 1.0
 */
public class ConstructorReferencesTest {
    //构造器引用
    //Supplier中的T get()
    //Employee的空参构造器：Employee()
    @Test
    public void test1(){
        //Lambda
        Supplier<Cat> sup1 = ()->new Cat();
        System.out.println(sup1.get());

        //constructor references
        Supplier<Cat> sup2 = Cat::new;
        System.out.println(sup2.get());
    }

    //Function中的R apply(T t)
    @Test
    public void test2(){
        //lambda
        Function<String,Cat> fun1 = name->new Cat(name);
        System.out.println(fun1.apply("金刚"));
        //constructor references
        Function<String,Cat> fun2 = Cat::new;
        System.out.println(fun2.apply("铁柱"));
    }
    //BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        //lambda
        BiFunction<String,String,Cat> fun1 = (name,color)->new Cat(name,color);
        System.out.println(fun1.apply("九尾", "白色"));
        //constructor references
        BiFunction<String,String,Cat> fun2 = Cat::new;
        System.out.println(fun2.apply("九尾", "黄色"));
    }
}

class Cat{
    private String name;
    private String color;

    public Cat() {
        this("Kitty","pink");
    }
    public Cat(String name) {
        this(name,"pink");
    }
    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
