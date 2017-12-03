package com.hendisantika.guava.cachingdemo.integration;

import com.google.common.cache.Cache;
import com.hendisantika.guava.cachingdemo.config.CacheConfig;
import com.hendisantika.guava.cachingdemo.controller.GuavaController;
import com.hendisantika.guava.cachingdemo.entity.PostCode;
import com.hendisantika.guava.cachingdemo.service.TemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.39
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureIntegrationTest {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    GuavaController controller;

    Cache postCodeCache;
    Cache temperatureCache;

    @Before
    public void setup() {
        postCodeCache = getAndInvalidate(CacheConfig.POSTCODE_CACHE);
        temperatureCache = getAndInvalidate(CacheConfig.TEMPERATURE_CACHE);
    }

    @Test
    public void testGetTemperatureStorePostCode() {
        String postcode = "1000AA";
        String temperatureForPostcode = controller.getTemperature(postcode).getBody();
        PostCode cachedPostcode = (PostCode) postCodeCache.asMap().get(postcode);
        assertThat(temperatureCache.asMap()).containsKey(cachedPostcode.getCoordinate());
        for (int i = 0; i < 100; i++) {
            controller.getTemperature("1234AA");
            assertThat(temperatureForPostcode).isEqualTo(controller.getTemperature(postcode).getBody());
        }
    }

    @Test
    public void testNullPostcodeIsAlsoCached() {
        assertThat(controller.getTemperature("1234XX").getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(postCodeCache.asMap().get("1234XX")).isInstanceOf(NullValue.class);

    }

    private Cache getAndInvalidate(String name) {

        Cache guavaCache = (Cache) cacheManager.getCache(name)
                .getNativeCache();


        assertThat(guavaCache).isNotNull();
        guavaCache.invalidateAll();
        return guavaCache;
    }
}
