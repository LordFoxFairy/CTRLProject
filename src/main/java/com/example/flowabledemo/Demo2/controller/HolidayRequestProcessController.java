package com.example.flowabledemo.Demo2.controller;
import com.example.flowabledemo.Demo2.dto.HolidayRequestDTO;
import com.example.flowabledemo.Demo2.service.HolidayRequestProcessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HolidayRequestProcessController {
    
    @Resource
    private HolidayRequestProcessService holidayRequestProcessService;
    
    @PostMapping("/holidayRequest")
    public void holidayRequest(@RequestBody HolidayRequestDTO holidayRequestDTO) {
        holidayRequestProcessService.holidayRequest(holidayRequestDTO);
    }
}
