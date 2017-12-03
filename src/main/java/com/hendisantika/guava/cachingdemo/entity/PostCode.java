package com.hendisantika.guava.cachingdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.11
 * To change this template use File | Settings | File Templates.
 */

@Entity(name = "Postcode")
public class PostCode implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private int coordinate;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getCoordinate() {
        return coordinate;
    }
}
