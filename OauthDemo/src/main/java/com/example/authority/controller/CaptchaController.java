package com.example.authority.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.google.code.kaptcha.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CaptchaController {
    
    @Resource
    Producer producer;
    
    @GetMapping("/captcha")
    public ResponseEntity<Object> Captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();
        
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        
//        redisUtil.hset(RedisConstant.CAPTCHA_KEY, key, code, 120);
        
        return ResultVO.success(
                MapUtil.builder()
                        .put("userKey", key)
                        .put("captcherImg", base64Img)
                        .build()
        );
    }
    
}
