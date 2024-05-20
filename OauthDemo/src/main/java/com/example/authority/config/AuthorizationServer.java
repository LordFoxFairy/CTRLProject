package com.example.authority.config;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.CustomConstants;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.enums.ResultCode;
import com.example.authority.exception.CustomWebResponseExceptionTranslator;
import com.example.authority.filter.CustomClientCredentialsTokenEndpointFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @Auther: zlx
 * @Date: 2020/4/18 16:04
 * @Description: 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    
    @Resource
    private JwtTokenStore jwtTokenStore;
    
    @Resource
    private AuthenticationManager authenticationManager;
    
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    
    @Resource
    private PasswordEncoder passwordEncoder;
    
    //1.客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CustomConstants.OAUTH2_CLIENT_ID)// 客户端id
                .secret(passwordEncoder.encode(CustomConstants.OAUTH2_CLIENT_SECRET))//客户端密钥
                .resourceIds(CustomConstants.OAUTH2_CLIENT_RESOURCE_ID)//资源列表
                .authorizedGrantTypes(CustomConstants.OAUTH2_GRANT_TYPE)// 该client允许的授权类型password
                .scopes(CustomConstants.OAUTH2_SCOPE)// 允许的授权范围
                .autoApprove(true);//true表示直接发放令牌
    }
    
    //2.配置令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)//密码模式需要配置
                .tokenServices(tokenService())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);//请求令牌运行post方式
        // 自定义异常转换类
        endpoints.exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }
    
    //3.配置令牌访问端点的安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
//        //注入自定义过滤器
//        String path = CustomConstants.OAUTH2_DEFAULT_TOKEN_PATH;
//        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(security, path);
//        endpointFilter.afterPropertiesSet();
//        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
//        security.addTokenEndpointAuthenticationFilter(endpointFilter);
//        security.authenticationEntryPoint(authenticationEntryPoint());
//
//        security
//                .tokenKeyAccess("permitAll()")                    //oauth/token_key是公开
//                .checkTokenAccess("isAuthenticated()");          //oauth/check_token需要登录
        
        
    }
    
    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setSupportRefreshToken(false);//支持刷新令牌
        service.setReuseRefreshToken(false);//是否复用refresh_token,默认为true(如果为false,则每次请求刷新都会删除旧的refresh_token,创建新的refresh_token)
        service.setTokenStore(jwtTokenStore);//令牌存储策略
        service.setAccessTokenValiditySeconds(CustomConstants.OAUTH2_TOKEN_VALIDITY_SECONDS); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(CustomConstants.OAUTH2_REFRESH_TOKEN_VALIDITY_SECONDS); // 刷新令牌默认有效期3天
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        return service;
    }
    
    //在认证服务器注入异常处理逻辑，自定义异常返回结果
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write(JSONUtil.toJsonPrettyStr(ResultVO.fail(ResultCode.CLIENT_AUTHENTICATION_FAILED)));
        };
    }
}