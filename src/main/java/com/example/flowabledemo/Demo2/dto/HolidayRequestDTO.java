package com.example.flowabledemo.Demo2.dto;

import lombok.Data;

@Data
public class HolidayRequestDTO {
    // 员工名
    private String employee;
    // 休假天数
    private Integer nrOfHolidays;
    // 备注
    private String description;
}


