package com.sherlock.distributelock.errorTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2020/11/24 10:23
 */
@RestController
@Slf4j
public class ErrorTestController {

    public static volatile Integer TOTAL = 10;
    @GetMapping("/com/sherlock/distributelock/errorTest")
    public void DecreaseStock() throws InterruptedException {
        if (TOTAL > 0) {
            TOTAL--;
            log.info(Thread.currentThread().getName() + " 下单成功！");
        }
        Thread.sleep(50);
        log.info("===错误示例=== 减完库存后,当前库存===" + TOTAL);
    }
}
