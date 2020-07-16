package com.zys.springbootquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author zhongyushi
 * @date 2020/7/16 0016
 * @dec 定时任务2
 */
public class MyJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context){
        System.out.println("我是定时任务2，现在时间是" + new Date());
    }
}
