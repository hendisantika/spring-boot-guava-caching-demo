package com.hendisantika.guava.cachingdemo.integration;

import com.google.common.cache.Cache;
import com.hendisantika.guava.cachingdemo.config.CacheConfig;
import com.hendisantika.guava.cachingdemo.entity.Shares;
import com.hendisantika.guava.cachingdemo.service.SharesService;
import com.hendisantika.guava.cachingdemo.service.StockExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.33
 * To change this template use File | Settings | File Templates.
 */

@SpringBootTest
class SharesIntegrationTest {
    @Autowired
    CacheManager cacheManager;
    Cache sharesCache;

    @Autowired
    SharesService sharesService;

    @Autowired
    StockExchange stockExchange;

    @BeforeEach
    void setup() {
        sharesCache = getAndInvalidate(CacheConfig.SHARES_CACHE);
    }

    @Test
    void testShares() {
        float value = sharesService.getValue(Shares.AKZO.name());
        assertThat(sharesCache.asMap()).containsKey("AKZO");
        //retrieves from cache:
        assertThat(sharesService.getValue(Shares.AKZO.name())).isEqualTo(value);
        stockExchange.invalidateAllPrices();
        float updatedValue = sharesService.getValue(Shares.AKZO.name());
        assertThat(value).isNotEqualTo(updatedValue);
        //retrieves from cache:
        assertThat(sharesService.getValue(Shares.AKZO.name())).isEqualTo(updatedValue);
    }

    private Cache getAndInvalidate(String name) {
        Cache guavaCache = (Cache) cacheManager.getCache(name)
                .getNativeCache();
        assertThat(guavaCache).isNotNull();
        guavaCache.invalidateAll();
        return guavaCache;
    }
}
