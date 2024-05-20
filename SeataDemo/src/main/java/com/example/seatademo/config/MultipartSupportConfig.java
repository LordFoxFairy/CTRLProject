package com.example.seatademo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;

/**
 * openFeign配置类
 *
 * @author 刘子固
 * @version 1.0
 * @date 2023/10/9 17:25
 */

public class MultipartSupportConfig implements RequestInterceptor {
    
    /**
     * 解决服务直接调用请求头不传递的问题
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        // 解决seata的xid未传递
        String xid = RootContext.getXID();
        template.header(RootContext.KEY_XID, xid);
    }
}
