package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.entity.Shares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.19
 * To change this template use File | Settings | File Templates.
 */

@Service
public class StockExchange {
    private Map<String, RotatingValue> shares = new HashMap<>();

    @PostConstruct
    void init() {
        for (Shares share : Shares.values())
            shares.put(share.name(), new RotatingValue());
    }

    @Autowired
    SharesService sharesService;

    public float getValue(String share) {
        return shares.get(validateName(share).name()).increment();
    }

    public void invalidateAllPrices() {
        shares.entrySet().stream().forEach(entry -> {
            float nextValue = entry.getValue().increment();
            sharesService.setNewSharePrice(entry.getKey(), nextValue);
        });
    }

    private Shares validateName(String name) {
        try {
            return Shares.valueOf(name);
        } catch (Exception e) {
            throw new RuntimeException("Not a valid share");
        }
    }
}
