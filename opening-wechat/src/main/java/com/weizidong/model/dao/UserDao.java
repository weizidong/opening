package com.weizidong.model.dao;

import com.weizidong.model.entity.User;
import com.weizidong.model.enums.DeleteStatus;
import com.weizidong.model.mapper.UserMapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户持久化
 *
 * @author 魏自东
 * @date 2018/2/8 15:57
 */
@Component
public class UserDao {
    @Resource
    private UserMapper userMapper;

    /**
     * 检查openid是否存在
     *
     * @param openid openid
     * @return 存在返回true，否则返回false
     * @author 魏自东
     * @date 2018/2/8 16:19
     */
    public boolean check(String openid) {
        User u = new User();
        u.setOpenid(openid);
        return userMapper.selectCount(u) > 0;
    }

    /**
     * 保存新用户
     *
     * @param u 新用户
     * @author 魏自东
     * @date 2018/2/8 16:24
     */
    public void create(User u) {
        u.setDeleted(DeleteStatus.未删除.getCode());
        u.setCreateTime(new Date());
        userMapper.insertSelective(u);
    }

    /**
     * 根据openid更新用户信息
     *
     * @param u 需要更新的用户资料
     * @author 魏自东
     * @date 2018/2/9 10:58
     */
    public void updateByOpenId(User u) {
        Example e = new Example(User.class);
        e.createCriteria().andEqualTo("openid", u.getOpenid());
        userMapper.updateByExampleSelective(u, e);
    }

    /**
     * 根据openid获取用户信息
     *
     * @param openid openid
     * @return 用户信息
     */
    public User getByOpenId(String openid) {
        User u = new User();
        u.setOpenid(openid);
        return userMapper.selectOne(u);
    }

    /**
     * 根据ID获取
     *
     * @param id id
     * @return 用户信息
     */
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
