package com.weizidong.service;

import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.filter.exception.ResponseCode;
import org.restful.api.filter.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务
 *
 * @author 魏自东
 * @date 2018/2/8 15:58
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 检查是否存在该openid
     *
     * @param openid openid
     * @return 存在返回true，否则返回false
     * @author 魏自东
     * @date 2018/2/8 16:22
     */
    public boolean check(String openid) {
        if (StringUtils.isBlank(openid)) {
            throw new WebException(ResponseCode.参数错误.getCode(), "openid 不能为空！");
        }
        return userDao.check(openid);
    }

    /**
     * 保存新用户
     *
     * @param u 新用户
     * @author 魏自东
     * @date 2018/2/8 16:24
     */
    public void create(User u) {
        userDao.create(u);
    }
}
