package com.example.exceldemo.controller;

import com.example.exceldemo.entity.UserEntity;
import com.example.exceldemo.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
@Api(tags = "Excel表格")
public class ExcelController {
    
    @SneakyThrows
    @ApiOperation(value = "导出")
    @PostMapping("/export")
    public void importExcel(HttpServletResponse response){
        List<UserEntity> data = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setAge(18);
        user.setName("胡桃");
        user.setSex(1);
        
        UserEntity user2 = new UserEntity();
        user2.setAge(18);
        user2.setName("妖刀姬");
        user2.setSex(0);
        
        data.add(user);
        data.add(user2);
        ExcelUtils.write(response, "人员数据.xls", "人员列表", UserEntity.class, data);
    }
    
    
    @SneakyThrows
    @ApiOperation(value = "导入")
    @PostMapping("/import")
    public Object importExcel(@RequestParam("file")MultipartFile file){
        return ExcelUtils.read(file, UserEntity.class);
    }
    
    
}
