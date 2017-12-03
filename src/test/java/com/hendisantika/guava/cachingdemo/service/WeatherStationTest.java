package com.hendisantika.guava.cachingdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.49
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherStationTest {
    @InjectMocks
    WeatherStation station;

    @Test
    public void testIncrements() {
        station.init();
        float previous = 0;
        for (int i = 0; i < 1000; i++) {
            float forCoordinate = station.getForCoordinate(15);
            assertThat(Math.abs(previous - forCoordinate)).isGreaterThan(0);
            previous = forCoordinate;
        }

    }

}