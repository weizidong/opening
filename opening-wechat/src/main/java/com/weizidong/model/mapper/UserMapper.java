package com.weizidong.model.mapper;

import com.weizidong.model.entity.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 魏自东
 * @date 2018/2/8 15:57
 */
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}