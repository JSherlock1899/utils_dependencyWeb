package com.sherlock.zkdistributelock.lock;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/25 11:18
 */
//使用多线程模拟多用户下单
public class OrderService implements Runnable{
    private CreateID cid = new CreateID();
    private ZookeeperLock lock = new ZookeeperDistributeLock();
    public void run() {
        try {
            lock.getLock();
            getNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unLock();
        }
    }

    public void getNumber() {
        String number = cid.getNumber();
        System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
    }

    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
        for (int i = 0; i < 10; i++) {
            new Thread(new OrderService()).start();
        }

    }

}
