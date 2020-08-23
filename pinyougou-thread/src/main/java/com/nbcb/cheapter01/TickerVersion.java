package com.nbcb.cheapter01;

public class TickerVersion implements Runnable{
    private int index = 1;
    private static int max = 500;
    private Object monitor = new Object();
    @Override
    public void run() {
        while (true){
            synchronized (monitor){
                if (index >max){
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"当前号码-->"+(index ++));
            }

        }

    }
}
