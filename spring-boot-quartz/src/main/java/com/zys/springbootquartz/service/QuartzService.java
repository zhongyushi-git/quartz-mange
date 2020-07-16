package com.zys.springbootquartz.service;

import com.github.pagehelper.PageInfo;
import com.zys.springbootquartz.entity.QuartzEntity;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 描述
 */
public interface QuartzService {


    int run(Long id) throws Exception;

    int stop(Long id) throws Exception;

    int createJob(QuartzEntity quartzEntity);

    int updateJob(QuartzEntity quartzEntity);

    int deleteJob(long id);

    PageInfo getList(Integer status,Integer page,Integer limit);

}
