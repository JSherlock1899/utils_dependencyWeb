package com.sherlock.zkdistributelock.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/25 11:18
 */
//生成订单号规则   时间戳+业务id
public class CreateID {
    //全局订单id
    public static int count = 0;

    public String getNumber() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpt.format(new Date()) + " --> " + ++count;
    }

}
