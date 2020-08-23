package com.nbcb.cheapter03;

/**
 *
 */
public class OtherService {

    //自定义一个锁
    private final Object lock = new Object();
    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1(){
        synchronized (lock){
            System.out.println("s1======================");
        }
    }
    public void s2(){
        synchronized (lock){
            System.out.println("s2======================");
            deadLock.m2();
        }
    }
}
