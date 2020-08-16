package com.nbcb.salary;

/**
 * 模拟线程采集数据
 */
public class ThreadJoin1 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CollectionData("M1",10000L));
        Thread t2 = new Thread(new CollectionData("M2",15000L));
        Thread t3 = new Thread(new CollectionData("M3",20000L));
        long endTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.printf("save data begin time is [%s],and stop time is [%s]",startTime,endTime);

    }


}
class CollectionData implements Runnable{
    private String machineName;
    private Long spendTime;

    public CollectionData(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName+"complete data Collection at timestamp [%s] and successFinally",System.currentTimeMillis());
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}