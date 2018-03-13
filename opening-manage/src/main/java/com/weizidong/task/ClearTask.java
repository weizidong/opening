package com.weizidong.task;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wzd.framwork.utils.*;

import java.io.File;
import java.util.Date;

/**
 * 清理相关调度
 *
 * @author 魏自东
 * @date 2018/2/7 9:37
 */
@Component
public class ClearTask {

    /**
     * 每天凌晨4点执行,清理文件夹
     *
     * @author 魏自东
     * @date 2018/2/7 9:37
     */
//    @Scheduled(cron = "0 0 4 * * ? ")
    public void clearDir() {
        clearDir(PoiExcelUtils.DOWNLOAD_URL);
    }

    /**
     * 清理文件夹
     *
     * @param dir 文件夹
     * @author 魏自东
     * @date 2018/2/7 9:37
     */
    private void clearDir(String dir) {
        try {
            Date d = new Date();
            LoggerUtils.info(getClass(), "开始清理【" + dir + "】文件夹...\t时间：" + DateUtil.dateToString(d, DateUtil.P_TIMESTAMP));
            FileUtils.cleanDirectory(new File(FileUtil.BASE_PATH + dir));
            LoggerUtils.info(getClass(), "清理【" + dir + "】文件夹完成！\t耗时：" + (System.currentTimeMillis() - d.getTime()) + " ms");
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "清理【" + dir + "】文件夹失败！", e);
        }
    }

}
