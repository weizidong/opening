package com.weizidong.service;

import com.github.pagehelper.PageInfo;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.dao.HouseDao;
import com.weizidong.model.dao.UserDao;
import com.weizidong.model.entity.User;
import com.weizidong.model.enums.DeleteStatus;
import com.weizidong.rest.dto.UserDto;
import com.weizidong.rest.dto.UserHouseDto;
import com.weizidong.rest.param.UserParam;
import com.weizidong.utils.WechatConfigs;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.session.SessionUtil;
import org.restful.api.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.*;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private HouseDao houseDao;
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
        return new PageInfo<>(userDao.list(param));
    }

    /**
     * 修改用户数据
     */
    public void update(User param) {
        User u = userDao.getById(param.getId());
        u.setName(param.getName());
        u.setPhone(param.getPhone());
        u.setIdNumber(param.getIdNumber());
        u.setAddress(param.getAddress());
        userDao.updateById(u);
    }

    /**
     * 删除用户数据
     */
    public void delete(Integer id) {
        User u = userDao.getById(id);
        u.setDeleted(DeleteStatus.已删除.getCode());
        userDao.updateById(u);
    }

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

    /**
     * 获取认筹记录
     *
     * @param param 分页参数
     * @return 认筹记录
     */
    public PageInfo<UserHouseDto> userList(PageParam param) {
        return new PageInfo<>(houseDao.userList(param));
    }

    /**
     * 导出认筹者列表
     *
     * @return excel地址
     */
    public String exportUserList() {
        PageParam param = new PageParam();
        param.setPageSize(null);
        List<UserHouseDto> data = houseDao.userList(param);
        String[] header = new String[]{
                "姓名@name@@4000",
                "电话@phone@@4000",
                "家庭住址@address@@4000",
                "身份证@idNumber@@4000",
                "楼栋号@buildingNo@num@4000",
                "楼层@floorNo@num@4000",
                "房号@roomNo@num@4000",
                "总价@totalPrice@money@4000"
        };
        return PoiExcelUtils.createExcel2FilePath("认筹记录", "认筹记录", FileUtil.BASE_PATH, header, data);
    }

    /**
     * 获取客户列表
     *
     * @param param 分页参数
     * @return 客户列表
     */
    public PageInfo<UserDto> allUserList(UserParam param) {
        return new PageInfo<>(userDao.allUserList(param));
    }
}
