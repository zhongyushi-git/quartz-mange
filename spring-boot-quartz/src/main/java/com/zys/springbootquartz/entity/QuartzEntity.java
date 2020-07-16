package com.zys.springbootquartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec Quartz实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quartz")
public class QuartzEntity {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 类名
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 分组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 任务状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * cron表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 任务描述
     */
    @Column(name = "description")
    private String description ;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public QuartzEntity(long id){
        this.id=id;
    }

    public QuartzEntity(long id,Integer status){
        this.id=id;
        this.status=status;
    }
}
