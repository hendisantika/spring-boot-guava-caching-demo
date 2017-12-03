package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.config.CacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.22
 * To change this template use File | Settings | File Templates.
 */

@Service
public class TemperatureService {
    private static Logger LOGGER = LoggerFactory.getLogger(TemperatureService.class);

    @Autowired
    WeatherStation weatherStation;

    @Cacheable(CacheConfig.TEMPERATURE_CACHE)
    public float getTemperatureForCoordinate(int coordinate) {
        LOGGER.info("Getting temperature from weather station {} ", coordinate);
        return weatherStation.getForCoordinate(coordinate);
    }
}
