package com.weizidong.filter;

import com.weizidong.exception.WeChatException;
import com.weizidong.model.entity.User;
import com.weizidong.service.UserService;
import com.weizidong.utils.WechatConfigs;
import com.weizidong.wechat.OAuth2;
import com.weizidong.wechat.WechatUser;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.session.Session;
import org.restful.api.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.wzd.framwork.utils.LoggerUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信授权过滤器
 *
 * @author WeiZiDong
 */
public class OAuth2Filter implements Filter {
    @Autowired
    private UserService userService;

    private static final String DEBUG_FLAG = "debug";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 获取访问请求
        String requestUrl = httpRequest.getRequestURI() + (httpRequest.getQueryString() != null ? "?" + httpRequest.getQueryString() : "");
        LoggerUtils.debug(getClass(), "请求:" + requestUrl);
        // 判断是否已登录
        Session session = SessionUtil.getSession(httpRequest);
        if (session != null) {
            // 已登录
            chain.doFilter(request, response);
            return;
        }
        // 未登录，获取token
        String token = SessionUtil.getToken(httpRequest);
        // 判断是否开启debug
        if (StringUtils.isNotBlank(httpRequest.getParameter(DEBUG_FLAG))) {
            // 开启debug模式
            token = httpRequest.getParameter(DEBUG_FLAG);
            LoggerUtils.debug(getClass(), "开启debug模式！\t" + "token：" + token);
        }
        // 尝试登陆
        if (StringUtils.isNotBlank(token)) {
            User user = userService.getByOpenId(token);
            if (user != null) {
                // 获取到用户信息
                SessionUtil.save(httpResponse, user.getOpenid(), user);
                chain.doFilter(request, response);
                return;
            }
        }
        // 判断是否是微信回调
        String code = httpRequest.getParameter("code");
        String start = httpRequest.getParameter("state");
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(start)) {
            // 是微信回调
            Long time1 = System.currentTimeMillis();
            LoggerUtils.debug(getClass(), "微信回调...\tcode:" + code + "\t耗时:" + (time1 - Long.parseLong(start)) + " ms");
            try {
                OAuth2 auth = new OAuth2();
                auth.getAccessToken(code);
                // 检查是否存在用户信息
                User u = userService.getByOpenId(auth.getOpenid());
                if (u == null) {
                    // 不存在,获取微信用户信息
                    WechatUser user = auth.getUserInfo();
                    LoggerUtils.debug(getClass(), "获取UserInfo:" + user + "\t耗时:" + (System.currentTimeMillis() - time1) + " ms");
                    // 转换成本地用户
                    u = User.convert(user);
                }
                // 保存登陆信息
                SessionUtil.save(httpResponse, u.getOpenid(), u);
                chain.doFilter(request, response);
            } catch (WeChatException e) {
                LoggerUtils.error(getClass(), e.getMessage(), e);
            }
            return;
        }
        // 去授权
        httpResponse.sendRedirect(OAuth2.authorize(WechatConfigs.getProperty("wechat.notify_url") + requestUrl, OAuth2.SNSAPI_USERINFO));
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        LoggerUtils.debug(getClass(), "微信授权过滤器\t启动成功...");
    }

}
