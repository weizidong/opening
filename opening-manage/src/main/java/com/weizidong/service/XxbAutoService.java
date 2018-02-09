package com.weizidong.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.clientUtil.RestClientUtil;

/**
 * XXBAutoService
 *
 * @author 魏自东
 * @date 2018/2/9 11:04
 */
@Service
public class XxbAutoService {

    private void createCourse() {
        JSONObject param = new JSONObject();
        param.put("courseName", "");
        param.put("grade", "");
        param.put("starTime", "");
        param.put("endTime", "");
        param.put("longitude", "");
        param.put("latitude", "");
        param.put("courseImg", "");
        param.put("courseLabels", "");
        param.put("itemId", "");
        JSONObject resp = RestClientUtil.postJson("/rest/course/createCourse", param, JSONObject.class);
    }
}
