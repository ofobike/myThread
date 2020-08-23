package com.nbcb.cheapter01;

public class TestController {
    public static void main(String[] args) {
        Thread t1 = new Thread(new TickerVersion(),"一号线程");
        Thread t2 = new Thread(new TickerVersion(),"二号线程");
        Thread t3 = new Thread(new TickerVersion(),"三号线程");
        t1.start();
        t2.start();
        t3.start();
    }
}
