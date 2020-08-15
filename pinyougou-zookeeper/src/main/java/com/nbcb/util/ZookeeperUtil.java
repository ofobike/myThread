package com.nbcb.util;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZookeeperUtil {
    private final static String ZKAddress = "192.168.6.134:2181";
    private final static int SESSIONTIMEOUT = 30000;
    private static volatile ZooKeeper zooKeeper;

    private ZookeeperUtil() {
    }

    public static ZooKeeper getConnection() {
        if (zooKeeper == null) {
            synchronized (ZookeeperUtil.class) {
                if (zooKeeper == null) {
                    CountDownLatch count = new CountDownLatch(1);
                    try {
                        zooKeeper = new ZooKeeper(ZKAddress, SESSIONTIMEOUT, event -> {
                            if (event.getType() == Watcher.Event.EventType.None) {
                                count.countDown();
                            }
                        });
                    } catch (Exception e) {
                        count.countDown();
                        e.printStackTrace();
                    }
                    try {
                        count.await(10L, TimeUnit.SECONDS);
                        if (zooKeeper.getState() == ZooKeeper.States.CONNECTING)
                            throw new RuntimeException("Connect To Zookeeper TimeOut");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return zooKeeper;
    }


}
