package com.zys.springbootquartz.controller;

import com.github.pagehelper.PageInfo;
import com.zys.springbootquartz.entity.CommonResult;
import com.zys.springbootquartz.entity.QuartzEntity;
import com.zys.springbootquartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 任务操作接口
 */
@RestController
@RequestMapping("/api/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    /**
     * 开启定时任务
     * @param id
     * @return
     */
    @GetMapping("/run")
    public CommonResult run(Long id){
        int count = 0;
        try {
            count = quartzService.run(id);
        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
        }finally {
            if(count!=0){
                return new CommonResult(200,"已开启任务");
            }else{
                return new CommonResult(444,"开启任务失败");
            }
        }
    }

    /**
     * 暂停定时任务
     */
    @GetMapping("/stop")
    public CommonResult stop(Long id){
        int count = 0;
        try {
            count = quartzService.stop(id);
        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
        }finally {
            if(count!=0){
                return new CommonResult(200,"已停止任务");
            }else{
                return new CommonResult(444,"停止任务失败");
            }
        }
    }

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/")
    public CommonResult createJob(@RequestBody QuartzEntity quartzEntity){
        int count=quartzService.createJob(quartzEntity);
        if(count!=0){
            return new CommonResult(200,"开启任务成功");
        }else{
            return new CommonResult(444,"开启任务失败");
        }
    }

    /**
     * 修改任务计划
     * @param quartzEntity
     * @return
     */
    @PutMapping("/")
    public CommonResult updateJob(@RequestBody QuartzEntity quartzEntity){
        int count=quartzService.updateJob(quartzEntity);
        if(count!=0){
            return new CommonResult(200,"修改任务成功");
        }else{
            return new CommonResult(444,"修改任务失败");
        }
    }

    /**
     * 删除任务
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommonResult deleteJob(@PathVariable("id")long id){
        int count=quartzService.deleteJob(id);
        if(count!=0){
            return new CommonResult(200,"删除任务成功");
        }else{
            return new CommonResult(444,"删除任务失败");
        }
    }

    @GetMapping("/list")
    public CommonResult getList(Integer status,Integer page,Integer limit){
        PageInfo list=quartzService.getList(status,page,limit);
        return new CommonResult(200,"查询成功",list.getList(),list.getTotal());
    }



}
