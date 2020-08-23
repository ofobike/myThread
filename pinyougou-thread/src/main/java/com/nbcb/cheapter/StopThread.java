package com.nbcb.cheapter;

/**
 * 停止线程的方法
 */
public class StopThread {
    private static class Worker extends Thread {
        protected volatile boolean stopFlag = true;

        @Override
        public void run() {
            while (stopFlag) {
                //
            }
        }

        public void shutdown() {
            this.stopFlag = false;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();
        //休眠
        Thread.sleep(10000);
        //调用终止线程方法
        worker.shutdown();
    }
}
