package com.weizidong.model.dao;

import com.weizidong.model.entity.Favorite;
import com.weizidong.model.entity.House;
import com.weizidong.model.mapper.FavoriteMapper;
import com.weizidong.model.mapper.HouseMapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
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
    private FavoriteMapper favoriteMapper;

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
     * 根据用户ID获取房源信息
     *
     * @param userId 用户ID
     * @return 房源信息
     */
    public House getByUserId(Integer userId) {
        House h = new House();
        h.setUserId(userId);
        List<House> houseList = houseMapper.select(h);
        if (houseList == null || houseList.isEmpty()) {
            return null;
        }
        return houseList.get(0);
    }

    /**
     * 检查用户是否已收藏
     *
     * @param userId  用户ID
     * @param houseId 房产ID
     * @return true：已收藏，false：未收藏
     */
    public Boolean checkFavorite(Integer userId, Integer houseId) {
        Favorite f = new Favorite();
        f.setHouseId(houseId);
        f.setUserId(userId);
        return favoriteMapper.selectCount(f) > 0;
    }

    /**
     * 创建收藏记录
     *
     * @param userId  用户ID
     * @param houseId 房产ID
     */
    public void createFavorite(Integer userId, Integer houseId) {
        Favorite f = new Favorite();
        f.setHouseId(houseId);
        f.setUserId(userId);
        f.setCreateTime(new Date());
        favoriteMapper.insertSelective(f);
    }

    /**
     * 删除指定用户的收藏记录
     *
     * @param houseId 房产ID
     * @param userId  用户ID
     */
    public void delFavorite(Integer houseId, Integer userId) {
        Favorite f = new Favorite();
        f.setUserId(userId);
        f.setHouseId(houseId);
        favoriteMapper.delete(f);
    }

    /**
     * 修改
     *
     * @param h 房源信息
     */
    public void update(House h) {
        houseMapper.updateByPrimaryKeySelective(h);
    }
}
