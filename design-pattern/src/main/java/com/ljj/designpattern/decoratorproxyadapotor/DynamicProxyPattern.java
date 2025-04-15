package com.ljj.designpattern.decoratorproxyadapotor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-15 9:47 a.m.
 * @Version 1.0
 */
public class DynamicProxyPattern {
    public static void main(String[] args) {
        IUserService service = new ImplUserService();

        //create the instance of InvocationHandler
        LoggingHandler handler = new LoggingHandler(service);

        //Dynamically generate proxy Object
        IUserService proxy = (IUserService) Proxy.newProxyInstance(
                //classloader
                IUserService.class.getClassLoader(),
                //proxy interface
                new Class[]{IUserService.class},
                //invocation
                handler);

        proxy.addUser();


    }
}

interface IUserService {
    void addUser();
}

class ImplUserService implements IUserService {

    @Override
    public void addUser() {
        System.out.println("Save User information！");
    }
}

/**
 * implement invocationHandler
 */
class LoggingHandler implements InvocationHandler {
    private Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking，perform data verification!");
        Object result = method.invoke(target, args);
        System.out.println("After invoking，record operation log");
        return result;
    }
}
