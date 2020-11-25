package com.sherlock.zkdistributelock.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/25 11:18
 */
public class ZookeeperDistributeLock extends ZookeeperAbstractLock {
    // 创建临时节点成功,返回true,创建失败,返回false
    Boolean tryLock() {
        try {
            zkClient.createEphemeralSequential(PATH,null);
            return true;
        } catch (RuntimeException e) {
            //e.printStackTrace();
            return false;
        }
    }

    void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            // 当节点发生删除时
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null) {
                    // 唤醒await()
                    countDownLatch.countDown();
                    System.out.println("节点发生删除");
                }
            }

            // 当节点发生改变时
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("节点发生改变");
            }
        };

        // 注册节点信息
        zkClient.subscribeDataChanges(PATH, iZkDataListener);
        if (zkClient.exists(PATH)) {
            countDownLatch = new CountDownLatch(1);
            try {
                // 等待,直到监听到节点被删除
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //删除事件通知
        zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
    }

}
