package com.weizidong;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * dao测试类基类，需要被继承
 *
 * @author WeiZiDong
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional(transactionManager = "transactionManager")
@Rollback
public class BasicTest {

    @After
    public void after() throws InterruptedException {
        Thread.sleep(1000);
    }
}