package com.weizidong.rest.param;

import com.qcdl.model.param.PageParam;

/**
 * opening
 *
 * @author 魏自东
 * @date 2018/4/10 11:32
 */
public class UserParam extends PageParam {
    /**
     * 电话号码
     */
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
