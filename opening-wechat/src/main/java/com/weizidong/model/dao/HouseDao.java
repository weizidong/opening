package com.weizidong.model.dao;

import com.weizidong.model.entity.House;
import com.weizidong.model.mapper.HouseMapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房源持久化
 *
 * @author 魏自东
 * @date 2018/2/8 15:57
 */
@Component
public class HouseDao {
    @Resource
    private HouseMapper houseMapper;

    /**
     * 获取房源列表
     *
     * @param buildingNo 楼栋号
     * @return 房源列表
     */
    public List<House> find(Integer buildingNo) {
        Example e = new Example(House.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("buildingNo", buildingNo);
        e.orderBy("floorNo").asc();
        e.orderBy("roomNo").asc();
        return houseMapper.selectByExample(e);
    }

    /**
     * 根据ID获取
     *
     * @param id 房子ID
     * @return 房子
     */
    public House getById(Integer id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量创建房子
     */
    public void create(List<House> h) {
        houseMapper.insertList(h);
    }

    /**
     * 获取楼栋列表
     *
     * @return 楼栋列表
     */
    public List<Integer> getBuilding() {
        return houseMapper.getBuilding();
    }
}
