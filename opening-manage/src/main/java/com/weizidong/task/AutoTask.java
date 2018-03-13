package com.weizidong.task;

import com.weizidong.service.XxbAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 自动调度任务
 *
 * @author 魏自东
 * @date 2018/2/9 11:02
 */
@Component
public class AutoTask {
    @Autowired
    private XxbAutoService xxbAutoService;

    /**
     * 每天凌晨1点执行
     *
     * @author 魏自东
     * @date 2018/2/7 9:37
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void autoTask() throws InterruptedException {
        xxbAutoService.doMain(224, 155, "高二快速提分", "http://oxpgahw70.bkt.clouddn.com//FiUNi5EVRapyK8hPcnxIVH7AiNMTT");
        xxbAutoService.doMain(208, 159, "高二快速提分", "http://oxpgahw70.bkt.clouddn.com//FiUNi5EVRapyK8hPcnxIVH7AiNMT");
    }
}
