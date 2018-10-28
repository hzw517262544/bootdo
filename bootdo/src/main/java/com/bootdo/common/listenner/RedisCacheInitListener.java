package com.bootdo.common.listenner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:2018-10-24
 * @since
 */
@Component
@Order(value = 2)
public class RedisCacheInitListener implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(RedisCacheInitListener.class);
    @Override
    public void run(String... args) throws Exception {
        logger.info("---------------将参数表数据缓存到redis缓存中---------------");
        try {

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("缓存参数表数据异常："+e.getMessage());
        }
    }
}
