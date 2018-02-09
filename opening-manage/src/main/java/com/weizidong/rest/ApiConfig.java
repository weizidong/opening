package com.weizidong.rest;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.restful.api.filter.authority.AuthorityFilter;
import org.restful.api.filter.exception.BaseExceptionMapper;
import org.restful.api.filter.formatjson.FormatJsonDynamicFeature;
import org.restful.api.filter.log.RequestLogDynamicFeature;
import org.springframework.web.filter.RequestContextFilter;

import javax.ws.rs.ApplicationPath;

/**
 * 注册接口
 *
 * @author 魏自东
 * @date 2018/2/6 10:53
 */
@ApplicationPath("/rest")
public class ApiConfig extends ResourceConfig {
    public ApiConfig() {
        this.packages(this.getClass().getPackage().getName());
        // Spring filter 提供了 JAX-RS 和 Spring 请求属性之间的桥梁
        register(RequestContextFilter.class);
        // 用 Jackson JSON 的提供者来解释 JSON
        register(FastJsonProvider.class);
        // 注册请求日志过滤器
        register(RequestLogDynamicFeature.class);
        // 操作日志记录
        // register(OperationLogFilter.class);
        // 将返回值转换为JsonResponse
        register(FormatJsonDynamicFeature.class);
        // 注册异常转换
        register(BaseExceptionMapper.class);
        // 文件上传
        register(MultiPartFeature.class);
        // 接口校验
        register(AuthorityFilter.class);
    }
}
