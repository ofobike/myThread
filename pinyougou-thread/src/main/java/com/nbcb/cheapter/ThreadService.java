package com.nbcb.cheapter;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

/**
 * 利用守护线程的特性关闭线程
 */
public class ThreadService {
    private Thread executeThread;
    private boolean finished = false;

    /**
     * 执行线程的方法
     * @param task
     */
    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    /**
     * 执行线程方法
     */
    public void shutdown(long mils) {
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if ((System.currentTimeMillis() - currentTime) >= mils){
                System.out.println("任务超时需要结束！");
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断！");
                break;
            }
        }
        finished = false;
    }
}
