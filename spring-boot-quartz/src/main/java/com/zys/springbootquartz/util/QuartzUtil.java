package com.zys.springbootquartz.util;

import org.quartz.Job;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 描述
 */
public class QuartzUtil {

    //获取job对象
    public static Job getJobClass(String classname) throws Exception{
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }
}
