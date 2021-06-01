package com.lagou.edu.filter;

import com.lagou.edu.feign.LagouTokenClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author izhouy
 * @title TokenCheckFilter
 * @Decription token有效校验
 * @CreateDate 2021/4/6 16:09
 */
@Slf4j
@Component // 让容器扫描到，等同于注册了
public class TokenCheckFilter implements GlobalFilter, Ordered {

    @Autowired
    LagouTokenClient lagouTokenClient;
    @Value("${register.num}")
    private Integer registerNum;

    // ip注册记录
    private static Map<String, Integer> ipRegisterMap = new ConcurrentHashMap<>();

    /**
     * 过滤器核心方法
     * @param exchange 封装了request和response对象的上下文
     * @param chain 网关过滤器链(包含全局过滤器和单路由过滤器)
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        if (Pattern.matches("^(/api/code/create).*", request.getURI().getPath())) {
            //获取验证码，通过
            return chain.filter(exchange);
        }
        if (Pattern.matches("^(/api/user/register).*", request.getURI().getPath())) {
            String clientIp = request.getRemoteAddress().getHostString();
            Integer num = 1;
            if(ipRegisterMap.containsKey(clientIp)){
                num = ipRegisterMap.get(clientIp) + 1;
                if(num > registerNum){
                    // 拒接访问，返回
                    response.setStatusCode(HttpStatus.FORBIDDEN); // 状态码
                    String data = "注册超过"+ registerNum+"次";
                    DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));
                    return response.writeWith(Mono.just(wrap));
                }
            }
            ipRegisterMap.put(clientIp, num);
            //注册接口，通过
            return chain.filter(exchange);
        }
        if (Pattern.matches("^(/api/user/login).*", request.getURI().getPath())) {
            //登录接口，通过
            return chain.filter(exchange);
        }
        HttpCookie httpCookie = request.getCookies().getFirst("token");
        if(httpCookie == null){
            // 拒接访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            String data = "请先注册登录";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }
        String token = httpCookie.getValue();

        if(token == null || "".equals(token)){
            // 拒接访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            String data = "请先注册登录";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }
        String email = lagouTokenClient.selectToken(token);
        if(email == null || "".equals(email)){
            // 拒接访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            String data = "无效TOKEN";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }

        // 合法请求，放行，执行后续的过滤器
        return chain.filter(exchange);
    }

    /**
     * 返回值表示当前过滤器的顺序(优先级)，数值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
