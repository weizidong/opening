package com.qcdl.daoTest;

import com.alibaba.fastjson.JSON;
import com.qcdl.model.dao.ConfigDao;
import com.qcdl.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ConfigDaoTest extends BasicTest {
    @Autowired
    private ConfigDao configDao;

    @Test
    public void test() {
        Map<String, Object> val = new HashMap<>();
        val.put("a", 1);
        val.put("b", "b");
        configDao.put("test", val);
        System.out.println(JSON.toJSONString(configDao.get("test")));
    }
}
