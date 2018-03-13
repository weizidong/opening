package com.weizidong.model.enums;

import com.alibaba.dubbo.rpc.exception.DubboException;

/**
 * opening
 *
 * @author 魏自东
 * @date 2018/3/13 16:14
 */
public enum SaleType {
    未售(0), 已售(1), 未推(2);

    int code;

    SaleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static SaleType valueOf(Integer code) {
        for (SaleType cs : SaleType.values()) {
            if (cs.getCode() == code) {
                return cs;
            }
        }
        throw new DubboException("认购类型【" + code + "】不是有效类型！");
    }
}
