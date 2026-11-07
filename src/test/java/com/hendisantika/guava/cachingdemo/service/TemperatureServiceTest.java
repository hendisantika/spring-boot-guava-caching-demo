package com.hendisantika.guava.cachingdemo.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class TemperatureServiceTest {
    @Mock
    WeatherStation weatherStation;

    @InjectMocks
    TemperatureService service;

    @Test
    void testInvalidValues() {
        service.getTemperatureForCoordinate(12);
        verify(weatherStation).getForCoordinate(12);
    }

}