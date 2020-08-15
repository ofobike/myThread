package com.nbcb.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DistributedLock {
    public static Logger log = LoggerFactory.getLogger(DistributedLock.class);
    private InterProcessMutex interProcessMutex;  //可重入排它锁
    private String lockName;  //竞争资源标志
    private String root = "/distributed/lock/";//根节点
    public static CuratorFramework curatorFramework;
    private static String ZK_URL = "192.168.6.134:2181";
    //创建client对象
    static{
        curatorFramework= CuratorFrameworkFactory.newClient(ZK_URL,new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();
    }


}
