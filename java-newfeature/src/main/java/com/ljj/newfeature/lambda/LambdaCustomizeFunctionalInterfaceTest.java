package com.ljj.newfeature.lambda;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-08 11:03 a.m.
 * @Version 1.0
 */
public class LambdaCustomizeFunctionalInterfaceTest {
    public static void main(String[] args) {
        doSomething(()-> System.out.println("Hello,"));
        doSomething(()-> System.out.println("Functional"));
        doSomething(()-> System.out.println("Interface!"));
    }
    public static void doSomething(MyInterface mi){
        mi.doIt();
    }
}
@FunctionalInterface
interface MyInterface{
    void doIt();
}
