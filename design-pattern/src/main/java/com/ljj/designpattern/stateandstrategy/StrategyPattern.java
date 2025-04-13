package com.ljj.designpattern.stateandstrategy;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-09 1:47 p.m.
 * @Version 1.0
 */
public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext(new MasterCreditPayment());
        paymentContext.pay(10);

        paymentContext.setPs(new VisaCreditPayment());
        paymentContext.pay(20);



    }
}

interface PaymentStrategy{
    void handle(int amount);
}

class MasterCreditPayment implements PaymentStrategy{

    @Override
    public void handle(int amount) {
        System.out.println("Use Master to Pay :"+ amount);
    }
}

class VisaCreditPayment implements PaymentStrategy{

    @Override
    public void handle(int amount) {
        System.out.println("Use Visa to Pay :"+ amount);
    }
}

class PaymentContext{
    private PaymentStrategy ps;
//    public PaymentContext(){}
    public PaymentContext(PaymentStrategy ps){
        this.ps = ps;
    }

    public void setPs(PaymentStrategy ps) {
        this.ps = ps;
    }

    public void pay(int amount){
        ps.handle(amount);
    }
}