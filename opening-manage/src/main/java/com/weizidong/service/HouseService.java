package com.weizidong.service;

import com.weizidong.model.dao.HouseDao;
import com.weizidong.model.entity.House;
import com.weizidong.model.entity.User;
import com.weizidong.model.enums.SaleType;
import org.restful.api.filter.exception.ResponseCode;
import org.restful.api.filter.exception.WebException;
import org.restful.api.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户业务
 *
 * @author 魏自东
 * @date 2018/2/8 15:58
 */
@Service
public class HouseService {
    @Autowired
    private HouseDao houseDao;


    /**
     * 获取房源列表
     *
     * @param buildingNo 楼栋号
     */
    public List<House> find(Integer buildingNo) {
        return houseDao.find(buildingNo);
    }

    /**
     * 购买房子
     *
     * @param houseId 房子ID
     * @param u       用户
     */
    public synchronized Map<String, Object> purchase(Integer houseId, User u) {
        House h = houseDao.getById(houseId);
        Assert.isFalse(h.getUserId() != null && h.getUserId() > 0, "该房产已被认购！");
        House db = houseDao.getByUserId(u.getId());
        if (db != null) {
            throw new WebException(ResponseCode.参数错误.getCode(), "您已经认购了房产【" + db.getRoomNo() + "】,不能再次认购！");
        }
        h.setUserId(u.getId());
        h.setStatus(SaleType.已售.getCode());
        h.setSalesTime(new Date());
        houseDao.update(h);
        Map<String, Object> res = new HashMap<>(16);
        res.put("phone", u.getPhone());
        res.put("name", u.getName());
        res.put("room", h.getRoomNo());
        res.put("time", h.getSalesTime());
        return res;
    }

    /**
     * 批量录入房源
     *
     * @param buildingSize 楼栋数
     * @param floorSize    楼层数量
     * @param roomSize     每楼房间数量
     * @param unitPrice    单价
     * @param area         房间面积
     */
    public void create(Integer buildingSize, Integer floorSize, Integer roomSize, Double unitPrice, Double area) {
        List<House> houseList = new ArrayList<>();
        for (int b = 1; b <= buildingSize; b++) {
            for (int f = 1; f <= floorSize; f++) {
                for (int r = 1; r <= roomSize; r++) {
                    House h = new House();
                    h.setBuildingNo(b);
                    h.setFloorNo(f);
                    h.setRoomNo(String.format("%d%02d%02d", b, f, r));
                    h.setArea(area);
                    h.setUnitPrice(unitPrice);
                    h.setTotalPrice(area * unitPrice);
                    h.setStatus(SaleType.未售.getCode());
                    houseList.add(h);
                }
            }
        }
        houseDao.create(houseList);
    }


    /**
     * 获取指定用户认筹的房子
     *
     * @param userId 用户ID
     * @return 房源信息
     */
    public House getMine(Integer userId) {
        if (userId == null || userId < 1) {
            return null;
        }
        return houseDao.getByUserId(userId);
    }

    /**
     * 收藏指定的房产
     *
     * @param userId  用户ID
     * @param houseId 房产ID
     */
    public void favorite(Integer userId, Integer houseId) {
        Boolean flag = houseDao.checkFavorite(userId, houseId);
        if (!flag) {
            // 未收藏，创建收藏记录
            houseDao.createFavorite(userId, houseId);
        }
    }

    /**
     * 删除指定用户的收藏记录
     *
     * @param houseId 房产ID
     * @param userId  用户ID
     */
    public void delFavorite(Integer houseId, Integer userId) {
        houseDao.delFavorite(houseId, userId);
    }
}
