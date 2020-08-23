package com.nbcb.cheapter02;

public class TickerVersion implements Runnable{
    private int index = 1;
    private final static  int MAX = 500;
    @Override
    public  void run() {
        while (true){
            synchronized (new Object()){
                if (index > MAX){
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"当前号码-->"+(index++));
            }

        }
    }
}
