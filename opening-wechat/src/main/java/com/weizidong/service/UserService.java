package com.weizidong.service;

import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import com.weizidong.wechat.Qrcode;
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
        Qrcode.create(1000, 1);
    }

    /**
     * 根据Openid更新用户信息
     *
     * @param u 需要更新的用户资料，必须包含openid
     * @author 魏自东
     * @date 2018/2/9 10:57
     */
    public void updateByOpenId(User u) {
        if (StringUtils.isBlank(u.getOpenid())) {
            throw new WebException(ResponseCode.参数错误.getCode(), "openid 不能为空！");
        }
        userDao.updateByOpenId(u);
    }

    /**
     * 根据openid获取用户信息
     *
     * @param openid openid
     * @return 用户信息
     */
    public User getByOpenId(String openid) {
        if (StringUtils.isBlank(openid)) {
            throw new WebException(ResponseCode.参数错误.getCode(), "openid 不能为空！");
        }
        return userDao.getByOpenId(openid);
    }

    /**
     * 绑定用户
     *
     * @param user 用户
     */
    public void bind(User user) {
    }

    /**
     * 根据ID获取
     *
     * @param id ID
     * @return 用户信息
     */
    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
