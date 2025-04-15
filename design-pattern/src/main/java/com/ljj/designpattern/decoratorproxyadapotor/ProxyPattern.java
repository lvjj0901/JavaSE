package com.ljj.designpattern.decoratorproxyadapotor;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-11 7:16 p.m.
 * @Version 1.0
 */
public class ProxyPattern {
    public static void main(String[] args) {
        UserService realService = new UserServiceImpl();

        UserServiceProxy proxy = new UserServiceProxy(realService);

        proxy.addUser();
    }
}

interface UserService {
    void addUser();
}

class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("insert one user!");
    }
}

class UserServiceProxy implements UserService {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addUser() {
        System.out.println("校验数据");
        userService.addUser();
        System.out.println("记录操作日志");
    }
}
