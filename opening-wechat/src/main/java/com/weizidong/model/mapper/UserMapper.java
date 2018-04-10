package com.weizidong.model.mapper;

import com.weizidong.model.entity.User;
import com.weizidong.rest.dto.UserDto;
import com.weizidong.rest.param.UserParam;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author 魏自东
 * @date 2018/4/10 13:41
 */
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    /**
     * 分页查询客户列表
     *
     * @param param 分页查询参数
     * @return 用户列表
     */
    List<UserDto> allUserList(UserParam param);
}