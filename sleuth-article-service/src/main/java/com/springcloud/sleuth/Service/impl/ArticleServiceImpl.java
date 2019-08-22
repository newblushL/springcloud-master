package com.springcloud.sleuth.Service.impl;

import com.springcloud.sleuth.Service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-22 14:25
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Async
    @Override
    public void saveLog(String log) {
        logger.info(log);
    }

    @NewSpan("saveLog2")
    @Override
    public void saveLog2(String log) {
        logger.info(log);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}