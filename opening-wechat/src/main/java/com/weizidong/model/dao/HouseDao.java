package com.weizidong.model.dao;

import com.github.pagehelper.PageHelper;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.entity.Favorite;
import com.weizidong.model.entity.House;
import com.weizidong.model.entity.HouseType;
import com.weizidong.model.mapper.FavoriteMapper;
import com.weizidong.model.mapper.HouseMapper;
import com.weizidong.model.mapper.HouseTypeMapper;
import com.weizidong.rest.dto.HouseDto;
import com.weizidong.rest.dto.UserHouseDto;
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
    @Resource
    private HouseTypeMapper houseTypeMapper;

    /**
     * 获取房源列表
     *
     * @param buildingNo 楼栋号
     * @return 房源列表
     */
    public List<House> find(Integer buildingNo) {
        return houseMapper.find(buildingNo);
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

    /**
     * 获取收藏的房源列表
     *
     * @param userId 用户Id
     * @return 收藏的房源列表
     */
    public List<HouseDto> getFavorite(Integer userId) {
        return houseMapper.getFavorite(userId);
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
    /**
     * 获取列表
     *
     * @param param 分页参数
     * @return 认筹者列表
     */
    public List<UserHouseDto> userList(PageParam param) {
        if (param.getPageSize() != null && param.getPageSize() > 0) {
            PageHelper.startPage(param.getPage(), param.getPageSize());
        }
        return houseMapper.userList();
    }
}
