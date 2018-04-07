package com.weizidong.rest.dto;

import com.weizidong.model.entity.House;

public class UserHouseDto extends House {
    /**
     * 电话
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 家庭住址
     */
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
