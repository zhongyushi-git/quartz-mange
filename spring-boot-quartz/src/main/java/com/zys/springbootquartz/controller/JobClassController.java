package com.zys.springbootquartz.controller;

import com.zys.springbootquartz.entity.CommonResult;
import com.zys.springbootquartz.service.JobClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 查询任务类接口
 */
@RestController
@RequestMapping("/api/clazz")
public class JobClassController {

    @Autowired
    private JobClassService jobClassService;

    @GetMapping("/list")
    public CommonResult getList(){
        List<Map<String, String>> list = jobClassService.getClassList();
        return new CommonResult(200,"查询成功",list);
    }
}
