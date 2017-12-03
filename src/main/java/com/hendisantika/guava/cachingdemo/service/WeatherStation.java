package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.entity.RotatingValue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.23
 * To change this template use File | Settings | File Templates.
 */

@Service
public class WeatherStation {
    private Map<Integer, RotatingValue> measurements = new HashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 10; i < 100; i++)
            measurements.put(i, new RotatingValue());
    }

    public float getForCoordinate(int coordinate) {
        return measurements.get(coordinate).increment();
    }
}
