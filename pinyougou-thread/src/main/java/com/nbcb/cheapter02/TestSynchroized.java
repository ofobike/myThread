package com.nbcb.cheapter02;

import com.nbcb.cheapter01.TickerVersion;

public class TestSynchroized {
    public static void main(String[] args) {
        final TickerVersion tickerVersion = new TickerVersion();
        Thread t1 = new Thread(tickerVersion,"一号窗口");
        Thread t2 = new Thread(tickerVersion,"二号窗口");
        Thread t3 = new Thread(tickerVersion,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
