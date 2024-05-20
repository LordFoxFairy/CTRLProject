package com.example.droolsdemo.DemoX.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("drl_rules")
public class DrlRule {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("rule_name")
    private String ruleName;
    
    @TableField("drl_content")
    private String drlContent;
}

