package com.weizidong.model.mapper;

import com.weizidong.model.entity.House;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author 魏自东
 * @date 2018/3/12 14:50
 */
public interface HouseMapper extends Mapper<House>, MySqlMapper<House> {
    /**
     * 获取楼栋列表
     *
     * @return 楼栋列表
     */
    List<Integer> getBuilding();
}