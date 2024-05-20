package com.example.authority.handler;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.enums.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会话信息过期策略
 * 同样的，当账号异地登录导致被挤下线时也要返回给前端json格式的数据，
 * 比如提示"账号下线"、"您的账号在异地登录，是否是您自己操作"或者"您的账号在异地登录,可能由于密码泄露，建议修改密码"等。
 * 这时就要实现SessionInformationExpiredStrategy（会话信息过期策略）来自定义会话过期时的处理逻辑。
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        ResponseEntity<Object> result = ResultVO.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
