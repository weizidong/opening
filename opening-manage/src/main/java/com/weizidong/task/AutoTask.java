package com.weizidong.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 自动调度任务
 *
 * @author 魏自东
 * @date 2018/2/9 11:02
 */
@Component
public class AutoTask {


    /**
     * 每天凌晨1点执行
     *
     * @author 魏自东
     * @date 2018/2/7 9:37
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void clearDir() {


    }



}
