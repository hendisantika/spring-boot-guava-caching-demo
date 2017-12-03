package com.hendisantika.guava.cachingdemo.entity;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.20
 * To change this template use File | Settings | File Templates.
 */
public class RotatingValue {
    private float value = 10.0f;
    private float increment = 0.1f;

    public RotatingValue() {
        value = 10.0f + (float) new Random(System.nanoTime()).nextInt(5);
    }

    public float increment() {
        increment = value < 10.0 || value > 20.0 ? -increment : increment;
        value += increment;
        return value;
    }
}
