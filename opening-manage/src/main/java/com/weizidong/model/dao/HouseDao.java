package com.weizidong.model.dao;

import com.weizidong.model.entity.House;
import com.weizidong.model.entity.HouseType;
import com.weizidong.model.mapper.HouseMapper;
import com.weizidong.model.mapper.HouseTypeMapper;
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
    @Resource
    private HouseTypeMapper houseTypeMapper;


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
     * 修改
     *
     * @param h 房源信息
     */
    public void update(House h) {
        houseMapper.updateByPrimaryKeySelective(h);
    }

    /**
     * 获取全部房源
     *
     * @return 房源列表
     */
    public List<House> all() {
        Example e = new Example(House.class);
        e.orderBy("buildingNo").asc().orderBy("floorNo").asc();
        return houseMapper.selectByExample(e);
    }

    /**
     * 获取房型列表
     */
    public List<HouseType> listType() {
        return houseTypeMapper.selectAll();
    }

    public void updateType(HouseType param) {
        houseTypeMapper.updateByPrimaryKeySelective(param);
    }

    public void deleteType(Integer id) {
        houseTypeMapper.deleteByPrimaryKey(id);
    }

    public boolean checkType(Integer id) {
        House h = new House();
        h.setHouseType(id);
        return houseMapper.selectCount(h) > 0;
    }

    public void addType(HouseType type) {
        houseTypeMapper.insertSelective(type);
    }
}
