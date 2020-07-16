package com.zys.springbootquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author zhongyushi
 * @date 2020/7/16 0016
 * @dec 定时任务3
 */
public class MyJob3 implements Job {
    @Override
    public void execute(JobExecutionContext context){
        Date date = new Date();
        System.out.println("我是定时任务3，出发时间："+date.getHours()+":"+date.getMinutes());
    }
}
