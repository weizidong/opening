package com.weizidong.rest.dto;

import com.weizidong.model.entity.User;

import java.util.Date;

/**
 * opening
 *
 * @author 魏自东
 * @date 2018/4/10 11:30
 */
public class UserDto extends User {
    /**
     * 认购的房源
     */
    private Integer houseId;
    /**
     * 楼栋号
     */
    private Integer buildingNo;

    /**
     * 楼层
     */
    private Integer floorNo;

    /**
     * 房号
     */
    private Integer roomNo;

    /**
     * 户型
     */
    private Integer houseType;

    /**
     * 销售时间
     */
    private Date salesTime;

    /**
     * 总价
     */
    private Double totalPrice;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public Date getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Date salesTime) {
        this.salesTime = salesTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
