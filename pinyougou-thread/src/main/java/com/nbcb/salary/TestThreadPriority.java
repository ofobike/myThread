package com.nbcb.salary;

import java.util.Optional;

/**
 * 学习线程的优先级别
 */
public class TestThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0;i<100;i++){
                Optional.of(Thread.currentThread().getName()+"index"+i).ifPresent(System.out::println);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(()->{
            for (int i = 0;i<100;i++){
                Optional.of(Thread.currentThread().getName()+"index"+i).ifPresent(System.out::println);
            }
        });
        t2.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
    }

}
