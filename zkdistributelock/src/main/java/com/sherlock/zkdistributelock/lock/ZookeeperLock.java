package com.sherlock.zkdistributelock.lock;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/25 11:16
 */
//锁 定义分布式锁
public interface ZookeeperLock {
    //获取锁
    void getLock();

    //释放锁
    void unLock();
}
