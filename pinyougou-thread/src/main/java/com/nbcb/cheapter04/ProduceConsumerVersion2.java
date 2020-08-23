package com.nbcb.cheapter04;

/**
 * 生产者消费者经典版本
 */
public class ProduceConsumerVersion2 {


    private int i = 1;

    private final Object lock = new Object();

    private volatile  boolean isProduce = false;



    public void produce(){
        synchronized (lock){
            if (isProduce){
                //等待
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                //开始生产数据
                i++;
                System.out.println("P->"+i);
                //唤醒消费
                lock.notify();
                isProduce = true;
            }
        }
    }



    public void consumer(){
        synchronized (lock){
            if (isProduce){
                System.out.println("C->"+i);
                //开始消费
                lock.notify();
                isProduce = false;
            }else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 version2 = new ProduceConsumerVersion2();
        new Thread(){
            @Override
            public void run() {
                while (true)
                version2.produce();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true)
                version2.consumer();
            }
        }.start();
    }
}
