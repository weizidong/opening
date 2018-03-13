package com.weizidong.service;

import com.github.pagehelper.PageInfo;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import com.weizidong.model.enums.DeleteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.PropertiesUtil;
import org.wzd.framwork.utils.QRCodeUtil;

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
    private static PropertiesUtil pu = new PropertiesUtil("/configs/config.properties");

    /**
     * 保存新用户
     *
     * @param user    新用户
     * @param adminId 管理员Id
     * @return 生成二维码
     * @author 魏自东
     * @date 2018/2/8 16:24
     */
    public String add(User user, Integer adminId) {
        user.setAdminId(adminId);
        user.setCreateTime(new Date());
        user.setDeleted(DeleteStatus.未删除.getCode());
        userDao.create(user);
        String qrcode = pu.getProperty("domain") + QRCodeUtil.encode(pu.getProperty("wechat") + "/wechatpage/bind.html?id=" + user.getId());
        user.setQrcode(qrcode);
        userDao.updateById(user);
        return qrcode;
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
