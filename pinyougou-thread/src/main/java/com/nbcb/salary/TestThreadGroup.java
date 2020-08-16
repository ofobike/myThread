package com.nbcb.salary;

import javax.swing.*;
import java.util.Arrays;

/**
 * 获取线程组的概念
 */
public class TestThreadGroup {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(300l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        //获取当前的线程组
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        //获取当前存在的线程数量
        System.out.println(group.activeCount());

        //获取当前那三个线程
        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);
        /*for (Thread thread:threads){
            System.out.println(thread);
        }*/
        Arrays.asList(threads).forEach(System.out::println);

    }
}
