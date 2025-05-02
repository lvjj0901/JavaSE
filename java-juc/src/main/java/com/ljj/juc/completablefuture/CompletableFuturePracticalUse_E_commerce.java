package com.ljj.juc.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Description:
 *1.需求：
 *  1.1在不同的电商平台搜索出同以产品的价格
 *  1.2在同一电商平台搜索出同一产品在不同卖家的价格
 *2.结果展示格式如下：
 *   <<mysql>> in jd price is 88.50
 *   <<mysql>> in dangdang price is 86.11
 *   <<mysql>> in taobao price is 90.43
 * 3.技术要求：
 *    3.1函数式编程
 *    3.2链式编程
 *    3.3 使用Stream API
 * @Author Jason Lyu
 * @Create 2025-05-01 9:08 a.m.
 * @Version 1.0
 */
public class CompletableFuturePracticalUse_E_commerce {
    static List<ECommerce> list = Arrays.asList(
            new ECommerce("jd"),
            new ECommerce("dangdang"),
            new ECommerce("bestbuy"),
            new ECommerce("newegg"),
            new ECommerce("tmall"),
            new ECommerce("taobao"));

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> prices = getPrices(list, "MySQL");
        prices.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("Time elapsed:"+(end-start));

        System.out.println("---------------------------");

        long start1 = System.currentTimeMillis();
        List<String> prices1 = getPricesByCompletableFuture(list, "Java");
        prices1.forEach(System.out::println);
        long end1 = System.currentTimeMillis();
        System.out.println("Time elapsed:"+(end1-start1));
    }

    public static List<String> getPricesByCompletableFuture(List<ECommerce> list,String itemName){
        return list.stream().map(eCommerce ->
                CompletableFuture.supplyAsync(()->itemName+
                        " in "+eCommerce.getECommerceName()+
                        " price is "+
                        eCommerce.getItemPrice(itemName))).
                collect(Collectors.toList()).//注意：只有执行中止操作，异步任务才会并行执行
                stream().map(task->task.join()).//此处统一收集异步任务结果
                collect(Collectors.toList());
    }
    public static List<String> getPrices(List<ECommerce> list,String itemName){
        List<String> list1 = list.stream().map(eCommerce ->
                String.format("<<" + itemName + ">> in %s price is %.2f",
                        eCommerce.getECommerceName(),
                        eCommerce.getItemPrice(itemName))).collect(Collectors.toList());
        return list1;
    }


}
@NoArgsConstructor
@Data
@Accessors
class ECommerce{
    private String eCommerceName;
    public ECommerce(String eCommerceName){
        this.eCommerceName = eCommerceName;
    }
    public double getItemPrice(String itemName){
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble()*+itemName.charAt(0);
    }




}
