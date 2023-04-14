package com.ggy.resource.util;


import com.ggy.resource.service.LikedService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 点赞的定时任务
 *  @title: LikeTask
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-31 17:52
 ***/

@Slf4j
public class LikeTask extends QuartzJobBean {
    @Autowired
    LikedService likedService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("LikeTask-------- {}", sdf.format(new Date()));
        //将 Redis 里的点赞信息同步到数据库里

        likedService.transLikedFromRedis2DB();
        likedService.transLikedCountFromRedis2DB();
    }
}
