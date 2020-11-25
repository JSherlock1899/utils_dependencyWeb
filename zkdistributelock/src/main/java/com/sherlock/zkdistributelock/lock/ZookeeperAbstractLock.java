package com.sherlock.zkdistributelock.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/25 11:16
 */
//重构重复代码,将重复代码交给子类执行
public abstract class ZookeeperAbstractLock implements ZookeeperLock{
    //zk连接地址
    private static final String ADDRESS = "39.107.90.231:2181";
    //创建zk连接
    protected ZkClient zkClient = new ZkClient(ADDRESS);
    //创建节点PATH
    protected static final String PATH = "/lock";

    protected CountDownLatch countDownLatch = null;


    public void getLock() {
        if(tryLock()){
            System.out.println("###成功获取锁###");
        }else{
            waitLock();
            getLock();
        }
    }

    //尝试获取锁
    abstract Boolean tryLock();
    //等待
    abstract void waitLock();
    public void unLock() {
        if(zkClient != null){
            zkClient.close();
            System.out.println("###成功释放锁资源###");
            System.out.println();
        }
    }

}
