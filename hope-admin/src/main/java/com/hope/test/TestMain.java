package com.hope.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（http://ilovey.live)
 * @Description: TODO
 * @Date: 19-5-8
 **/
public class TestMain {
    public static void main(String[] args) {
        //构造一个线程池
        ThreadPoolExecutor threadPool=new ThreadPoolExecutor(
                1,1,1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
                for (int a =0;a<10;a++){
                    System.out.println(a+=1);
                }
                System.out.println("aa");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("b");
                for (int a =0;a<10;a++){
                    System.out.println(a+=1);
                }
                System.out.println("bb");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("c");
                for (int a =0;a<10;a++){
                    System.out.println(a+=1);
                }
                System.out.println("cc");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("d");
                int [] temp =new int[10];
                for (int a =0;a<10;a++){
                    temp[a]=a+1;
                }
                for (int t:temp){
                    System.out.println(t);
                }
                System.out.println("dd");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("e");
                for (int a =0;a<10;a++){
                    System.out.println(a+=1);
                }
                System.out.println("ee");
            }
        });
    }
}
