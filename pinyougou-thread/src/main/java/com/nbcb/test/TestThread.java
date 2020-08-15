package com.nbcb.test;

public class TestThread {
    public static void main(String[] args){
        Thread thread = new Thread("Customer-thread"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        thread.start();
        //thread.start();
    }
}
