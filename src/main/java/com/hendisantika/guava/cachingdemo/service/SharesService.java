package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.config.CacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.18
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SharesService {
    private static Logger LOGGER = LoggerFactory.getLogger(SharesService.class);
    @Autowired
    StockExchange exchange;

    @CachePut(cacheNames = CacheConfig.SHARES_CACHE, key = "#share")
    public float setNewSharePrice(String share, float nextValue) {
        LOGGER.info("Share {} was updated to {}", share, nextValue);
        return nextValue;
    }

    @Cacheable(CacheConfig.SHARES_CACHE)
    public float getValue(String shareName) {
        LOGGER.info("Fetching share {} from exchange", shareName);
        return exchange.getValue(shareName);
    }
}
