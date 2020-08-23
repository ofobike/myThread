package com.nbcb.cheapter04;

public class ProduceConsumerVersion1 {
    private int i = 1;
    private final Object lock = new Object();

    public void produce(){
        synchronized (lock){
            System.out.println("P-->"+(i++));
        }
    }

    public void consume(){
        synchronized (lock){
            System.out.println("C-->"+i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 version1 = new ProduceConsumerVersion1();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    version1.produce();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    version1.consume();
                }
            }
        }.start();
    }
}
