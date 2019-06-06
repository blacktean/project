package com.bankTransfer.web;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class DynamicScheduleTaskSecond {
//	@Autowired
//	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
//	private ScheduledFuture<?> future;
//
//	private String cron = "";
//
//	public String getCron() {
//		return cron;
//	}
//
//	public void setCron(String cron) {
//		this.cron = cron;
//		stopCron();
//		future = threadPoolTaskScheduler.schedule(new Runnable() {
//
//			@Override
//			public void run() {
//					System.out.println("Msg:定时任务执行成功");
//				
//			}
//		}, new Trigger() {
//			@Override
//			public Date nextExecutionTime(TriggerContext triggerContext) {
//				if ("".equals(cron) || cron == null)
//					return null;
//				CronTrigger trigger = new CronTrigger(cron);// 定时任务触发，可修改定时任务的执行周期
//				Date nextExecDate = trigger.nextExecutionTime(triggerContext);
//				return nextExecDate;
//			}
//		});
//	}
//
//	public void stopCron() {
//		if (future != null) {
//			future.cancel(true);// 取消任务调度
//		}
//	}
}