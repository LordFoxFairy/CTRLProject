package com.example.exceldemo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.exceldemo.convert.EConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)// EasyExcel 使用了cglib，而cglib读取链接调用存在问题
public class UserVO {
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty(value = "用户性别", converter = EConverter.class)
    private Integer sex;
}
