package com.nbcb.domain;

import com.nbcb.util.ZookeeperUtil;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LockLogic {
    private static final Logger LOG = LoggerFactory.getLogger(LockLogic.class);

    private final Thread currentThread = Thread.currentThread();

    private final static String separator = "/";

    private String
            preNode,//监听的前一个节点（如果有的话）
            lockName,//锁名称
            lockId;//最终创建成功的节点名称（锁名称+序号）

    private StringBuilder temp; //用于处理锁名称的缓存对象
    private ZooKeeper zooKeeper;
    private LockWatcher lockWatcher; //自定义Watcher


    public LockLogic(String lockName) {
        temp =  new StringBuilder(separator).append(lockName);//此时temp为 "/lockName"
        this.lockName = temp.toString();
        this.zooKeeper = ZookeeperUtil.getConnection();
        this.lockWatcher = new LockWatcher();
    }

    private class LockWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            //只处理节点删除事件 进行唤醒
            if (event.getType() == Event.EventType.NodeDeleted){
                System.out.println(Thread.currentThread().getName()+"在监听器中将唤醒线程" +currentThread.getName());
                synchronized (currentThread){
                    currentThread.notify();
                }
            }
        }
    }

    /**
     * 获取锁
     */
    public void getLock() {
        try {
            createNode();
        } catch (KeeperException | InterruptedException e) {
            LOG.error("创建ZooKeeper节点发生异常 : {}",e);
            throw new RuntimeException(e);
        }
        try {
            List<String> childrenList = zooKeeper.getChildren(lockName, false);//获取子节点不需要加监听，否则节点发生变化时会形成惊群效应
            if (!dealWithChildrenList(childrenList)) { //如果没有获取到锁，则对前一个节点注册监听器
                Stat exists = zooKeeper.exists(preNode, lockWatcher);
                if (exists != null) { //若前一个节点存在则等待
                    System.out.println("获取"+lockId+"锁失败;将监听节点"+preNode);
                    synchronized (currentThread){
                        currentThread.wait();
                    }
                }
            }
            System.out.println(currentThread.getName()+"获取到分布式锁"+lockId);
        } catch (KeeperException | InterruptedException e) {
            LOG.error("获取分布式锁发生异常 {}", e);
            throw new RuntimeException(e);
        }
    }
    private void createNode() throws KeeperException, InterruptedException {
        try {
            //这里之所以直接创建锁节点，是因为ZooKeeper的exits方法返回时间为9ms左右，而create方法普遍耗时在30ms以上
            //并发情况下会有大量客户端判断该节点不存在后去执行create方法而导致出现异常所以直接忽略KeeperException.NodeExistsException
            zooKeeper.create(lockName, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException.NodeExistsException ignore) {}
        lockId = zooKeeper.create(temp.append(separator).toString(), null,ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        //temp为“/lockName/”， 创建的节点格式为“/lockName/0000000000” “/lockName/0000000001”
        System.out.println("线程"+currentThread.getName()+"创建节点"+lockId);
    }


    private boolean dealWithChildrenList(List<String> childrenList) {
        if (childrenList.isEmpty()) return true;

        //实现一个Comparator 接口对所有子节点进行排序 ：0000000000 0000000001 0000000002
        if (childrenList.size() > 1) childrenList.sort((str1, str2) -> {
            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);
            return (num1 >= num2) ? ((num1 == num2) ? 0 : 1) : -1;
        });

        int tempLength = temp.length();
        String firstChild = childrenList.get(0);
        temp.append(firstChild);//拼接当前最小子节点的名字

        if (lockId.equals(temp.toString())) return true;//如果自己持有的时最小节点则获取到锁
        else preNode = temp.replace(tempLength,temp.length(),childrenList.get(childrenList.indexOf(lockId.substring(tempLength))-1)).toString();
        return false;
    }
    public void unLock() {
        try {
            //delete(final String path, int version)； -1表示任意版本号
            zooKeeper.delete(lockId, -1);
            System.out.println(currentThread.getName()+"释放到分布式锁"+lockId);
        } catch (Exception e) {
            LOG.error("删除节点异常{}", e);
        }
    }
}
