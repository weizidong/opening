package com.weizidong.service;

import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import com.weizidong.utils.WechatConfigs;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.session.SessionUtil;
import org.restful.api.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.QRCodeUtil;
import org.wzd.framwork.utils.RegexUtils;

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
        Assert.isFalse(StringUtils.isBlank(openid), "openid 不能为空！");
        return userDao.check(openid);
    }

    /**
     * 保存新用户
     *
     * @param u 新用户
     * @author 魏自东
     * @date 2018/2/8 16:24
     */
    public String create(User u) {
        userDao.create(u);
        return QRCodeUtil.encode(WechatConfigs.getProperty("wechat.notify_url") + "/wechatpage/bind.html?id=" + u.getId());
    }

    /**
     * 根据Openid更新用户信息
     *
     * @param u 需要更新的用户资料，必须包含openid
     * @author 魏自东
     * @date 2018/2/9 10:57
     */
    public void updateByOpenId(User u) {
        Assert.isFalse(StringUtils.isBlank(u.getOpenid()), "openid 不能为空！");
        userDao.updateByOpenId(u);
    }

    /**
     * 根据openid获取用户信息
     *
     * @param openid openid
     * @return 用户信息
     */
    public User getByOpenId(String openid) {
        Assert.isFalse(StringUtils.isBlank(openid), "openid 不能为空！");
        return userDao.getByOpenId(openid);
    }

    /**
     * 绑定用户
     *
     * @param user 用户
     */
    public void bind(User user) {
        Assert.isFalse(user.getId() == null || user.getId() < 1, "二维码有误！");
        Assert.isTrue(StringUtils.isNotBlank(user.getIdNumber()) && RegexUtils.checkIdCard(user.getIdNumber()), "身份证号码填写错误！");
        User db = userDao.getById(user.getId());
        Assert.notNull(db, "该用户未登记！");
        Assert.isTrue(StringUtils.isBlank(db.getOpenid()), "该用户已绑定！");
        Assert.isTrue(StringUtils.equalsIgnoreCase(user.getIdNumber(), db.getIdNumber()), "身份证号码填写错误！");
        userDao.updateById(user);
        user.setName(db.getName());
        user.setAddress(db.getAddress());
        user.setPhone(db.getPhone());
        SessionUtil.update(user.getOpenid(), user);
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
