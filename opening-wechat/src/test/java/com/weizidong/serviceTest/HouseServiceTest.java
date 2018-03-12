package com.weizidong.serviceTest;

import com.weizidong.BasicTest;
import com.weizidong.service.HouseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HouseServiceTest extends BasicTest {
    @Autowired
    private HouseService houseService;

    @Test
    public void test() {
        houseService.create(4, 20, 6, 8000.0, 84.5);
    }

    @Test
    public void test1() {
        houseService.find(1);
    }

    @Test
    public void test2() {
        houseService.getBuilding();
    }
}
