package com.nbcb.exception;

import com.nbcb.domain.LockLogic;
import com.nbcb.util.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class DemoZookeeper {
    private static final String PATH = "/example/pathCache";
    private static final String NODE_PATH = "/example/nodeCache";
    private static final String TREE_PATH = "/example/treeCache";
    public static void main(String[] args) throws Exception {
        CuratorFramework client = DistributedLock.curatorFramework;
        //创建一个节点，初始内容为空 如果没有设置节点属性，节点创建模式默认为持久化节点，内容默认为空
        /*String path = client.create().forPath("/day01");
        System.out.println(path);*/
        //创建一个节点，附带初始化内容
       /* String path = client.create().forPath("/day02", "init".getBytes());
        System.out.println(path);*/
        //创建一个节点，指定创建模式（临时节点），内容为空
        /*String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/day03");
        System.out.println(path);*/
        //创建一个节点，指定创建模式（临时节点--顺序节点），内容为空
        /*String path = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/day04");
        System.out.println(path);*/
        //创建一个持久类型的顺序节点
       /* String path = client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/day05");
        System.out.println(path);*/
       //获取节点中的数据
       /* List<String> path = client.getChildren().forPath("/");
        for (String str:path){
            System.out.println(str);
        }*/
        //PathCacheMain(client);
        //监听某一个特定的节点
        //NodeCacheTest(client);
        //TreeCacheTest(client);

        //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/YSJ");

        /*IntStream.range(0, 10).forEach(i -> new Thread(() -> {
                    LockLogic lockLogic = new LockLogic("lock");
                    lockLogic.getLock();
                    function();
                    lockLogic.unLock();
                }).start()
        );*/
        Map<String,String> result = new HashMap<>();
        String put = result.put("123", "2");
        System.out.println(put);
    }

    private static void function() {
        System.out.println("function execute");
    }

    private static void TreeCacheTest(CuratorFramework client) throws Exception {
        TreeCache cache = new TreeCache(client, TREE_PATH);

        cache.start();
        cache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType() +
                        " | 路径：" + (null != event.getData() ? event.getData().getPath() : null));
            }
        });
        client.create().creatingParentsIfNeeded().forPath(TREE_PATH);
        client.setData().forPath(TREE_PATH, "01".getBytes());
        Thread.sleep(100);
        client.setData().forPath(TREE_PATH, "02".getBytes());
        Thread.sleep(100);
        client.delete().deletingChildrenIfNeeded().forPath(TREE_PATH);
        Thread.sleep(1000 * 2);
        cache.close();
        client.close();
        System.out.println("OK!");
    }

    private static void NodeCacheTest(CuratorFramework client) throws Exception {
        client.create().creatingParentsIfNeeded().forPath(NODE_PATH);
        //创建节点的监听类
        final NodeCache nodeCache = new NodeCache(client, NODE_PATH);
        //监听这个节点
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                //获取当前的节点数据
                ChildData data = nodeCache.getCurrentData();
                if (null != data) {
                    System.out.println("节点数据：" + new String(nodeCache.getCurrentData().getData()));
                } else {
                    System.out.println("节点被删除!");
                }
            }
        });
        nodeCache.start();
        client.setData().forPath(NODE_PATH, "01".getBytes());
        Thread.sleep(100);
        client.setData().forPath(NODE_PATH, "02".getBytes());
        Thread.sleep(100);
        client.delete().deletingChildrenIfNeeded().forPath(NODE_PATH);
        Thread.sleep(1000 * 2);
        nodeCache.close();
        client.close();
        System.out.println("OK!");
    }

    private static void PathCacheMain(CuratorFramework client) throws Exception {
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();
        //监听节点数据
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型--->"+event.getType());
                //获取数据
                ChildData data = event.getData();
                if (null != data){
                    System.out.println("节点数据路径-->" + event.getData().getPath() + " = " + new String(event.getData().getData()));
                }
            }
        });
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        Thread.sleep(10);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());
        Thread.sleep(10);
        client.setData().forPath("/example/pathCache/test01", "01_V2".getBytes());
        Thread.sleep(10);
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
        client.delete().forPath("/example/pathCache/test01");
        Thread.sleep(10);
        client.delete().forPath("/example/pathCache/test02");
        Thread.sleep(1000 * 5);
        cache.close();
        client.close();
        System.out.println("OK!");
    }
}
