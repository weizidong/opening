package com.weizidong.daoTest;

import com.qcdl.model.dao.ConfigDao;
import com.weizidong.BasicTest;
import com.weizidong.model.dao.HouseDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wzd.framwork.utils.MathUtil;

public class ConfigDaoTest extends BasicTest {
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private HouseDao houseDao;

    @Test
    public void test() {
//        Map<String, Object> val = new HashMap<>();
//        val.put("a", 1);
//        val.put("b", "b");
//        configDao.put("test", val);
//        System.out.println(JSON.toJSONString(configDao.get("test")));
        System.out.println(MathUtil.getUUID());
    }

}
