package com.ljj.designpattern.decoratorproxyadapotor;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-11 7:16 p.m.
 * @Version 1.0
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());

        coffee = new CreamDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());

        coffee = new SugarDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());
        System.out.println();
        System.out.println("Yes, but I wanna a Double Double!");

        coffee = new CreamDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());
        coffee = new SugarDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());
    }
}

interface Coffee{
    double getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee{

    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee ";
    }
}

abstract class DecoratedCoffee implements Coffee{
    protected Coffee decoratedCoffee;
    public DecoratedCoffee(Coffee coffee){
        this.decoratedCoffee =  coffee;
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

class CreamDecorator extends DecoratedCoffee{

    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost()+0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+",one Cream ";
    }
}

class SugarDecorator extends DecoratedCoffee{

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost()+0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+",one Cream ";
    }
}