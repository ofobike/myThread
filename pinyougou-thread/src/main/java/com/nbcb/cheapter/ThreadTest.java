package com.nbcb.cheapter;

import java.util.Optional;

/**
 * 线程终止方法
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while (true){
                //Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
            }
        },"MyThread");
        t.start();
        t.join();
        //停止线程
        Optional.of(t.isInterrupted()).ifPresent(System.out::println);
        t.interrupt();
        Optional.of(t.isInterrupted()).ifPresent(System.out::println);

        Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
    }
}
