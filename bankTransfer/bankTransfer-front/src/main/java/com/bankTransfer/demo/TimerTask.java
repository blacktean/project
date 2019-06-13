package com.bankTransfer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bankTransfer.util.HandleTransfer;

/**
 * 定时器
 * @author Administrator
 *
 */
@Component
public class TimerTask {
	@Autowired
	private HandleTransfer handleTransfer;
	@Scheduled(cron="0 0 * * * ?")    // 间隔30秒执行
    public void test(){
        try {
        	handleTransfer.Handle();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
