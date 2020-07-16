package com.zys.springbootquartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zys.springbootquartz.dao.QuartzDao;
import com.zys.springbootquartz.entity.QuartzEntity;
import com.zys.springbootquartz.service.QuartzService;
import com.zys.springbootquartz.util.QuartzUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec Quartz实现类，在这里执行定时任务
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    //注入quartz的任务调度器
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzDao quartzDao;

    @Override
    public PageInfo getList(Integer status,Integer page,Integer limit){
        PageHelper.startPage(page, limit);
        Example example=new Example(QuartzEntity.class);
        if(status!=null){
            example.createCriteria().andEqualTo("status",status);
        }
        List<QuartzEntity> list = quartzDao.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int run(Long id) throws Exception {
        QuartzEntity quartzEntity = getQuartzById(id);
        runJob(quartzEntity.getClassName(), quartzEntity.getGroupName(), quartzEntity.getCronExpression());
        return quartzDao.updateByPrimaryKeySelective(new QuartzEntity(id, 1));
    }

    @Override
    public int stop(Long id) throws Exception {
        QuartzEntity key = getQuartzById(id);
        stopJob(key.getClassName(), key.getGroupName());
        return quartzDao.updateByPrimaryKeySelective(new QuartzEntity(id, 0));
    }

    @Override
    public int createJob(QuartzEntity quartzEntity) {
        quartzEntity.setStatus(0);
        quartzEntity.setCreateTime(new Date());
        return quartzDao.insertSelective(quartzEntity);
    }

    @Override
    public int updateJob(QuartzEntity quartzEntity) {
        return quartzDao.updateByPrimaryKeySelective(quartzEntity);
    }

    @Override
    public int deleteJob(long id) {
        QuartzEntity quartzEntity = new QuartzEntity(id);
        return quartzDao.deleteByPrimaryKey(quartzEntity);
    }

    public QuartzEntity getQuartzById(long id) {
        QuartzEntity quartzEntity = new QuartzEntity(id);
        return quartzDao.selectByPrimaryKey(quartzEntity);
    }

    //开启
    public void runJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(QuartzUtil.getJobClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    //暂停
    public void stopJob(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }
}
