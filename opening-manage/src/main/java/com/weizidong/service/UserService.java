package com.weizidong.service;

import com.github.pagehelper.PageInfo;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import com.weizidong.model.enums.DeleteStatus;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.session.SessionUtil;
import org.restful.api.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.QRCodeUtil;
import org.wzd.framwork.utils.RegexUtils;

import java.util.Date;

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
     * 保存新用户
     *
     * @param user    新用户
     * @param adminId 管理员Id
     * @author 魏自东
     * @date 2018/2/8 16:24
     */
//    public String create(User u) {
//        userDao.create(u);
//        return QRCodeUtil.encode(WechatConfigs.getProperty("wechat.notify_url") + "/wechatpage/bind.html?id=" + u.getId());
//    }
    public String add(User user, Integer adminId) {
        user.setAdminId(adminId);
        user.setCreateTime(new Date());
        user.setDeleted(DeleteStatus.未删除.getCode());
        userDao.create(user);
        return QRCodeUtil.encode()
    }

    /**
     * 获取用户列表
     *
     * @param param 分页参数
     * @return 用户列表
     */
    public PageInfo<User> list(PageParam param) {
        return null;
    }
}
