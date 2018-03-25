package com.weizidong.service;

import com.github.pagehelper.PageInfo;
import com.weizidong.model.dao.HouseDao;
import com.weizidong.model.entity.House;
import com.weizidong.model.entity.HouseType;
import com.weizidong.model.enums.SaleType;
import org.restful.api.filter.exception.ResponseCode;
import org.restful.api.filter.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    h.setRoomNo(r);
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
     * 获取所有的房源
     *
     * @return 房源列表
     */
    public Map<Integer, Map<Integer, List<House>>> all() {
        List<House> houseList = houseDao.all();
        Map<Integer, Map<Integer, List<House>>> res = new HashMap<>(16);
        houseList.forEach((house) -> {
            if (!res.containsKey(house.getBuildingNo())) {
                res.put(house.getBuildingNo(), new HashMap<>(16));
            }
            Map<Integer, List<House>> build = res.get(house.getBuildingNo());
            if (!build.containsKey(house.getFloorNo())) {
                build.put(house.getFloorNo(), new ArrayList<>());
            }
            build.get(house.getFloorNo()).add(house);
        });
        return res;
    }

    /**
     * 添加房型
     */
    public void addType(HouseType type) {
        type.setId(null);
        houseDao.addType(type);
    }

    /**
     * 获取房型列表
     */
    public List<HouseType> listType() {
        return houseDao.listType();
    }

    /**
     * 修改房型
     */
    public void updateType(HouseType param) {
        houseDao.updateType(param);
    }

    /**
     * 删除房型
     */
    public void deleteType(Integer id) {
        if (houseDao.checkType(id)) {
            throw new WebException(ResponseCode.存在子菜单.getCode(), "该房型正在被使用，无法删除！");
        }
        houseDao.deleteType(id);
    }
}
