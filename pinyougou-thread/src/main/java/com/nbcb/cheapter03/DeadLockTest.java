package com.nbcb.cheapter03;

import java.lang.reflect.WildcardType;

public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock();

        otherService.setDeadLock(deadLock);
        deadLock.setOtherService(otherService);


        new Thread(){
            @Override
            public void run() {
                while (true)
                deadLock.m1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true)
                otherService.s2();
            }
        }.start();
    }
}
