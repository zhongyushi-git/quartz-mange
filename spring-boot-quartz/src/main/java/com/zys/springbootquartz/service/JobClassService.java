package com.zys.springbootquartz.service;

import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 任务类服务，查询有多少个任务类
 */

public interface JobClassService {

    List<Map<String,String>> getClassList();
}
