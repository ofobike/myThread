package com.nbcb.domain;

import javax.swing.*;
import java.util.Map;

/**
 * 两个线程模拟对这个数据进行操作
 */
public class Person {
    Integer i = 0;

    /**
     * 模拟数据
     * @param args
     */
    public static void main(String[] args) {

        Person person = new Person();
        /**
         * 线程一
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    person.i ++;
                    System.out.println(person.i);

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.i++;
                System.out.println(person.i);

            }
        }).start();
    }
}
