package com.nbcb.cheapter;

/**
 * 暴力关闭线程
 */
public class StopThread3 {
    public static void main(String[] args) {
       ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
        threadService.execute(()->{
            /*while (true){

            }*/
            //线程5秒执行完成任务
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //执行10秒结束
        threadService.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
