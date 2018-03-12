package com.weizidong.model.mapper;

import com.weizidong.model.entity.House;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface HouseMapper extends Mapper<House>, MySqlMapper<House> {
}