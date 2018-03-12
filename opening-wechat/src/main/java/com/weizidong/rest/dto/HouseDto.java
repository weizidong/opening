package com.weizidong.rest.dto;

import com.weizidong.model.entity.House;

public class HouseDto extends House {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
