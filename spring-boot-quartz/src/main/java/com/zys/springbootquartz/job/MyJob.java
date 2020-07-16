package com.zys.springbootquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 任务1,
 */
//所有的任务都放在这个包下
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context){
        System.out.println("定时任务测试1：" + new Date());
    }
}
