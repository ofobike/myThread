package com.nbcb.salary;

import java.io.InputStream;
import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        t.start();
        t.join();
        IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
    }
}
