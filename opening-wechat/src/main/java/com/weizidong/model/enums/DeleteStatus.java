package com.weizidong.model.enums;

import com.alibaba.dubbo.rpc.exception.DubboException;


/**
 * 删除状态
 *
 * @author 魏自东
 * @date 2018/2/8 16:13
 */
public enum DeleteStatus {
    未删除(0), 已删除(1);

    int code;

    DeleteStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static DeleteStatus valueOf(Integer code) {
        for (DeleteStatus cs : DeleteStatus.values()) {
            if (cs.getCode() == code) {
                return cs;
            }
        }
        throw new DubboException("删除状态【" + code + "】不是有效状态！");
    }
}
