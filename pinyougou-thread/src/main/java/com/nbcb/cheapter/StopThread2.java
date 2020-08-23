package com.nbcb.cheapter;

public class StopThread2 {
    public static class MyThread implements Runnable{
        @Override
        public void run() {
            while (true){
                if (Thread.interrupted()){
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        Thread.sleep(10000L);
        //终断
        thread.interrupt();
    }
}
