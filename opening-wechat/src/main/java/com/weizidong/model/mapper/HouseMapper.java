package com.weizidong.model.mapper;

import com.weizidong.model.entity.House;
import com.weizidong.rest.dto.HouseDto;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 获取收藏的房源列表
     *
     * @param userId 用户Id
     * @return 收藏的房源列表
     */
    List<HouseDto> getFavorite(@Param("userId") Integer userId);
}