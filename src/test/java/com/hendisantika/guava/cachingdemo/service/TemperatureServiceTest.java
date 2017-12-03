package com.hendisantika.guava.cachingdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.48
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceTest {
    @Mock
    WeatherStation weatherStation;

    @InjectMocks
    TemperatureService service;

    @Test
    public void testInvalidValues() {
        service.getTemperatureForCoordinate(12);
        verify(weatherStation).getForCoordinate(12);
    }

}