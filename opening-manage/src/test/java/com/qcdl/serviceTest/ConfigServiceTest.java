package com.qcdl.serviceTest;

import com.alibaba.fastjson.JSON;
import com.qcdl.service.impl.ConfigServiceI;
import com.qcdl.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ConfigServiceTest extends BasicTest {
    @Autowired
    private ConfigServiceI configService;

    @Test
    public void test() {
        Map<String, Object> val = new HashMap<>();
        val.put("a", 1);
        val.put("b", "b");
        configService.put("test", val);
        configService.put("test", val);
        System.out.println(JSON.toJSONString(configService.get("test")));
    }
}
