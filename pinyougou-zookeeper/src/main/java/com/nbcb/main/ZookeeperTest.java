package com.nbcb.main;


import com.sun.xml.internal.ws.api.server.Adapter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ZookeeperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperTest.class);
    private static final String ZKAddress = "192.168.6.134:2181";

    public static void main(String[] args) throws Exception {
        getNodes();
    }

    public static void getNodes() throws Exception {

        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        //利用工厂模式创建数据
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(ZKAddress)
                .sessionTimeoutMs(20000)
                .connectionTimeoutMs(20000)
                .retryPolicy(retry)
                .namespace("base")
                .build();
        //启动
        client.start();
        //获取节点的状态
        CuratorFrameworkState state = client.getState();
        LOGGER.info("state-->{}",state);
        //检测节点变化
        List<String> children = client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                LOGGER.info("监听--->{}",event.toString());
            }
        }).forPath("/q1");

        LOGGER.info("children----->{}",children);
        /*String result= client.create().forPath("/q1","ceshissss".getBytes());
        System.out.println(result);*/
        //获取节点中数据
//        byte[] path = client.getData().forPath("/q1");
//        String data = new String(path);
//        System.out.println(data);
        //System.out.println(getNodeType(client,"/base/path"));
        //获取子节点
        /*List<String> strings = client.getChildren().forPath("/base");
        for (String str:strings){
            System.out.println(str);

        }*/
       /* Stat stat = client.setData().forPath("/q1", "杨少杰".getBytes());
        System.out.println(stat);*/

        /*Void path = client.delete().forPath("/q1");
        System.out.println(path);*/
        CreateMode type = getNodeType(client, "/path");
        System.out.println(type);
    }

    /**
     * 判断节点的类型
     *
     * @param client
     * @param path
     * @return
     */
    public static CreateMode getNodeType(CuratorFramework client, String path) {
        try {

            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                return null;
            }
            if (stat.getEphemeralOwner() > 0) {
                return CreateMode.EPHEMERAL;
            }
            return CreateMode.PERSISTENT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
