package com.bankTransfer;

import java.text.SimpleDateFormat;
import java.util.Date;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import  org.springframework.scheduling.annotation.Scheduled;
import  org.springframework.stereotype.Component;
 
 
@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private  static final SimpleDateFormat dataFromat = new SimpleDateFormat("HH:mm:ss");
 
    @Scheduled(fixedRate = 5*60*1000)
    public void reportCurrent(){
        logger.info("现在时间：{}",dataFromat.format(new Date()));
    }
 
}
