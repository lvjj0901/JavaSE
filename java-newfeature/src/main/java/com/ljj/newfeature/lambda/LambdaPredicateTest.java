package com.ljj.newfeature.lambda;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-08 10:40 a.m.
 * @Version 1.0
 */
public class LambdaPredicateTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("world");
        list.add("I");
        list.add("am");
        list.add("Java");
        list.add("Lambda!");

        System.out.println("-----print before updating");
        list.forEach(s -> System.out.println(s));

        list.removeIf(s -> s.contains("o"));

        System.out.println("-----print after updating");

        list.forEach(s -> System.out.println(s));
    }

    @Test
    public void testCollectionPredicate() {
        List<String> list = new ArrayList<>();//Arrays.asList("Andy", "Jason", "Wanda");
        list.add("Andy");
        list.add("Jason");
        list.add("Wanda");
        list.forEach(s -> System.out.println(s));
        System.out.println();

        list.removeIf(str -> str.contains("o"));

        list.forEach(s -> System.out.println(s));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 所有员工对象
     */
    @Test
    public void testCollectionPredicate1() {
        //get All employees
        EmployeeSerice employeeSerice = new EmployeeSerice();
        ArrayList<Employee> employees = employeeSerice.get(t -> true);
        employees.forEach(s -> System.out.println(s));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 所有年龄超过35的员工
     */
    @Test
    public void testCollectionPredicate2() {
        //Get all employees older than 35
        EmployeeSerice employeeSerice = new EmployeeSerice();
        ArrayList<Employee> employees = employeeSerice.get(employee -> employee.age > 35);

        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 所有薪资高于15000的女员工
     */
    @Test
    public void testCollectionPredicate3() {
        EmployeeSerice employeeSerice = new EmployeeSerice();
        ArrayList<Employee> employees = employeeSerice.get(employee -> (employee.getSalary() > 15000) && (employee.gender == '女'));
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 所有编号是偶数的员工
     */
    @Test
    public void testCollectionPredicate4() {
        EmployeeSerice employeeSerice = new EmployeeSerice();
        ArrayList<Employee> employees = employeeSerice.get(employee -> employee.getId() % 2 == 0);
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 名字是“张三”的员工
     */
    @Test
    public void testCollectionPredicate5() {
        EmployeeSerice employeeSerice = new EmployeeSerice();
        ArrayList<Employee> employees = employeeSerice.get(employee -> employee.getName().equals("张三"));
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 在测试类中创建EmployeeSerice员工管理类的对象，并调用get方法，分别获取：
     * - 年龄超过25，薪资低于10000的男员工
     */
    @Test
    public void testCollectionPredicate6() {
        EmployeeSerice employeeSerice = new EmployeeSerice();
        employeeSerice.get(employee -> employee.age > 25 && employee.gender == '男'&&employee.getSalary() < 10000).forEach(employee -> System.out.println(employee));
    }

    class EmployeeSerice {
        private ArrayList<Employee> all;

        public EmployeeSerice() {
            all = new ArrayList<Employee>();
            all.add(new Employee(1, "张三", '男', 33, 8000));
            all.add(new Employee(2, "翠花", '女', 23, 18000));
            all.add(new Employee(3, "无能", '男', 46, 8000));
            all.add(new Employee(4, "李四", '女', 23, 9000));
            all.add(new Employee(5, "老王", '男', 23, 15000));
            all.add(new Employee(6, "大嘴", '男', 23, 11000));
        }

        ArrayList<Employee> get(Predicate<Employee> p) {
            ArrayList<Employee> result = new ArrayList<>();
            for (Employee emp : all) {
                if (p.test(emp)) {
                    result.add(emp);
                }
            }

            return result;
        }
    }

    class Employee {
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
}
