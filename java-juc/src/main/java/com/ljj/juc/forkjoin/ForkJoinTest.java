package com.ljj.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 * compute 1+2+3+4+5+...+100
 * @Author Jason Lyu
 * @Create 2025-04-28 10:57 a.m.
 * @Version 1.0
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        //1.define task
        BusinessTask task = new BusinessTask(1, 100);

        //2.define execution for object
        ForkJoinPool fjp = new ForkJoinPool();

        //3.execute task
        ForkJoinTask<Integer> result = fjp.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
class BusinessTask extends RecursiveTask<Integer>{
    private int begin;
    private int end;
    private int threshold = 10;


    public BusinessTask(int begin,int end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if((end-begin)<=threshold){
            int result = 0;
            for (int i = begin; i <=end ; i++) {
                result += i;
            }
            return result;
        }else {
            int middle = (begin+end)/2;
            BusinessTask left = new BusinessTask(begin, middle);
            BusinessTask right = new BusinessTask(middle + 1, end);
//            left.fork();
//            result = right.compute()+left.join();
            left.fork();
            right.fork();
            return left.join()+right.join();
        }
    }
}
