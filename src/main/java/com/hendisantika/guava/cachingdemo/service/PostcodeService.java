package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.config.CacheConfig;
import com.hendisantika.guava.cachingdemo.entity.PostCode;
import com.hendisantika.guava.cachingdemo.repository.PostcodeRepo;
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
 * Time: 08.15
 * To change this template use File | Settings | File Templates.
 */

@Service
public class PostcodeService {
    private static Logger LOGGER = LoggerFactory.getLogger(PostcodeService.class);

    @Autowired
    PostcodeRepo postcodeRepo;

    @Cacheable(CacheConfig.POSTCODE_CACHE)
    public PostCode getPostcode(String code) {
        LOGGER.info("Getting postcode {} from dbase", code);
        return postcodeRepo.findByCode(code);
    }
}
