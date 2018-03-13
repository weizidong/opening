package com.weizidong.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class House implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 楼栋号
     */
    @Column(name = "building_no")
    private Integer buildingNo;

    /**
     * 楼层
     */
    @Column(name = "floor_no")
    private Integer floorNo;

    /**
     * 房号
     */
    @Column(name = "room_no")
    private String roomNo;

    /**
     * 认筹者ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 户型
     */
    @Column(name = "house_type")
    private Integer houseType;

    /**
     * 销售时间
     */
    @Column(name = "sales_time")
    private Date salesTime;

    /**
     * 建筑面积
     */
    private Double area;

    /**
     * 单价
     */
    @Column(name = "unit_price")
    private Double unitPrice;

    /**
     * 总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 状态：0、未售，1、已售，2、未推
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取楼栋号
     *
     * @return building_no - 楼栋号
     */
    public Integer getBuildingNo() {
        return buildingNo;
    }

    /**
     * 设置楼栋号
     *
     * @param buildingNo 楼栋号
     */
    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    /**
     * 获取楼层
     *
     * @return floor_no - 楼层
     */
    public Integer getFloorNo() {
        return floorNo;
    }

    /**
     * 设置楼层
     *
     * @param floorNo 楼层
     */
    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    /**
     * 获取房号
     *
     * @return room_no - 房号
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * 设置房号
     *
     * @param roomNo 房号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * 获取认筹者ID
     *
     * @return user_id - 认筹者ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置认筹者ID
     *
     * @param userId 认筹者ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取户型
     *
     * @return house_type - 户型
     */
    public Integer getHouseType() {
        return houseType;
    }

    /**
     * 设置户型
     *
     * @param houseType 户型
     */
    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    /**
     * 获取销售时间
     *
     * @return sales_time - 销售时间
     */
    public Date getSalesTime() {
        return salesTime;
    }

    /**
     * 设置销售时间
     *
     * @param salesTime 销售时间
     */
    public void setSalesTime(Date salesTime) {
        this.salesTime = salesTime;
    }

    /**
     * 获取建筑面积
     *
     * @return area - 建筑面积
     */
    public Double getArea() {
        return area;
    }

    /**
     * 设置建筑面积
     *
     * @param area 建筑面积
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * 获取单价
     *
     * @return unit_price - 单价
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单价
     *
     * @param unitPrice 单价
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取总价
     *
     * @return total_price - 总价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总价
     *
     * @param totalPrice 总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取状态：0、未售，1、已售，2、未推
     *
     * @return status - 状态：0、未售，1、已售，2、未推
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0、未售，1、已售，2、未推
     *
     * @param status 状态：0、未售，1、已售，2、未推
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", buildingNo=").append(buildingNo);
        sb.append(", floorNo=").append(floorNo);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", userId=").append(userId);
        sb.append(", houseType=").append(houseType);
        sb.append(", salesTime=").append(salesTime);
        sb.append(", area=").append(area);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        House other = (House) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBuildingNo() == null ? other.getBuildingNo() == null : this.getBuildingNo().equals(other.getBuildingNo()))
            && (this.getFloorNo() == null ? other.getFloorNo() == null : this.getFloorNo().equals(other.getFloorNo()))
            && (this.getRoomNo() == null ? other.getRoomNo() == null : this.getRoomNo().equals(other.getRoomNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getHouseType() == null ? other.getHouseType() == null : this.getHouseType().equals(other.getHouseType()))
            && (this.getSalesTime() == null ? other.getSalesTime() == null : this.getSalesTime().equals(other.getSalesTime()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getUnitPrice() == null ? other.getUnitPrice() == null : this.getUnitPrice().equals(other.getUnitPrice()))
            && (this.getTotalPrice() == null ? other.getTotalPrice() == null : this.getTotalPrice().equals(other.getTotalPrice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBuildingNo() == null) ? 0 : getBuildingNo().hashCode());
        result = prime * result + ((getFloorNo() == null) ? 0 : getFloorNo().hashCode());
        result = prime * result + ((getRoomNo() == null) ? 0 : getRoomNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getHouseType() == null) ? 0 : getHouseType().hashCode());
        result = prime * result + ((getSalesTime() == null) ? 0 : getSalesTime().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getUnitPrice() == null) ? 0 : getUnitPrice().hashCode());
        result = prime * result + ((getTotalPrice() == null) ? 0 : getTotalPrice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}